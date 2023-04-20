package com.example.fundamentals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.fundamentals.fragments.FragmentOne
import com.example.fundamentals.fragments.FragmentTwo
import com.example.fundamentals.interfaces.FragmentOneListener
import com.example.fundamentals.interfaces.FragmentTwoListener

class MainActivity : AppCompatActivity(), FragmentOneListener, FragmentTwoListener {
    lateinit var fragmentOne: FragmentOne
    lateinit var fragmentTwo: FragmentTwo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentTwo = FragmentTwo()
        fragmentOne = FragmentOne()

        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().replace(R.id.fragment_container_two, fragmentTwo).commit()
        fm.beginTransaction().replace(R.id.fragment_container_one, fragmentOne).commit()
    }

    override fun onDataReceivedOne(data: String) {
        fragmentTwo.updateData(data)
    }

    override fun onDataReceivedTwo(data: String) {
        fragmentOne.updateData(data)
    }
}