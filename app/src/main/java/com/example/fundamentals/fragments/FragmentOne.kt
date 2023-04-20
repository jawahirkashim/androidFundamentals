package com.example.fundamentals.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fundamentals.R
import com.example.fundamentals.databinding.FragmentOneBinding
import com.example.fundamentals.interfaces.FragmentOneListener

class FragmentOne : Fragment(R.layout.fragment_one) {
    lateinit var listener: FragmentOneListener
    lateinit var binding: FragmentOneBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentOneListener) {
            listener = context
        } else {
            throw RuntimeException("main activity did not implement interface")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOneBinding.bind(view)
        sendData()
    }

    private fun sendData() {
        if (::listener.isInitialized) {
            listener.onDataReceivedOne("Fragment one says Hello!!")
        }
    }

    fun updateData(data: String) {
        if (::binding.isInitialized)
            binding.textViewFragmentOne.text = data
    }
}