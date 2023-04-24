package com.example.fundamentals

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ServerService : Service() {
    private val binder = ServerBinder()
    private var message: String? = null
    private val myLiveData = MutableLiveData<String>()

    fun getMyLiveData(): LiveData<String> {
        return myLiveData
    }

    // Call this method whenever you want to update the data in the LiveData object
    private fun updateMyLiveData(newValue: String) {
        CoroutineScope(Dispatchers.IO).launch {
            for (a in 0..40) {
                Log.d("ServerService", "updateMyLiveData: ")
                delay(500)
                myLiveData.postValue("$a -- $newValue")
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    fun setMessage(msg: String?) {
        Log.d("ServerService", "setMessage: $msg")
        message = msg
        updateMyLiveData("$msg acknowledged from service!!!!!")
    }

    fun getMessage(): String? {
        return message
    }

    inner class ServerBinder : Binder() {
        fun getService(): ServerService {
            return this@ServerService
        }
    }
}
