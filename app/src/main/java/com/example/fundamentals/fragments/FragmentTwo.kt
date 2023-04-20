package com.example.fundamentals.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fundamentals.R
import com.example.fundamentals.databinding.FragmentTwoBinding
import com.example.fundamentals.interfaces.FragmentTwoListener

class FragmentTwo : Fragment(R.layout.fragment_two) {
    lateinit var listener: FragmentTwoListener
    lateinit var binding: FragmentTwoBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentTwoListener) {
            listener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTwoBinding.bind(view)
        sendData()
    }

    private fun sendData() {
        if (::listener.isInitialized) {
            listener.onDataReceivedTwo("Fragment Two says Hello!!")
        }
    }

    fun updateData(data: String) {
        if (::binding.isInitialized)
            binding.textViewFragmentTwo.text = data

    }
}