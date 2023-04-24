package com.example.fundamentals

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fundamentals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var serverService: ServerService? = null
    private var isBound = false
    lateinit var binding:ActivityMainBinding
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as ServerService.ServerBinder
            serverService = binder.getService()
            isBound = true

            // Observe the LiveData object in the service
            serverService!!.getMyLiveData().observe(this@MainActivity) { newValue ->
                Log.d("MainActivity", "onServiceConnected: $newValue")
                binding.textView.text = newValue
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bindIntent = Intent(this, ServerService::class.java)
        bindService(bindIntent, connection, Context.BIND_AUTO_CREATE)
        
        binding.button.setOnClickListener {
            if (isBound) {
                val message = binding.editText.text.toString()
                serverService?.setMessage(message)
                Toast.makeText(this, "Message set successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }
}