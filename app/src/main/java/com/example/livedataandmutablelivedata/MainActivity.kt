package com.example.livedataandmutablelivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val firstmutableLiveData : MutableLiveData<Int> = MutableLiveData()
    private val secondmutableLiveData : MutableLiveData<Int> = MutableLiveData()
    private val mediatorLiveData : MediatorLiveData<Int> = MediatorLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val minus = findViewById<Button>(R.id.minus)
        val plus = findViewById<Button>(R.id.plus)


        var count = 0



        plus.setOnClickListener {
            count++
            firstmutableLiveData.value = count
        }


        minus.setOnClickListener {
            count--
            secondmutableLiveData.value = count
        }

        mediatorLiveData.addSource(firstmutableLiveData){
            mediatorLiveData.value = it
        }

        mediatorLiveData.addSource(secondmutableLiveData){
            mediatorLiveData.value = it
        }

        mediatorLiveData.observe(this, Observer {
            Toast.makeText(this,"Counter = $it",Toast.LENGTH_SHORT).show()
        })

//        getLiveData().observe(this, Observer {
//            Toast.makeText(this,"Counter = ${it}",Toast.LENGTH_SHORT).show()
//        })

    }

//    private fun getLiveData() : LiveData<Int> = mutableLiveData
}