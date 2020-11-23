package com.appsdeviser.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mListOfPerson = listOf(Person("Alex",19), Person("Bella",28), Person("Cat",37), Person("Doug",46), Person("Elle",55), Person("Fire",64), Person("Gary",73), Person("Hales",82), Person("Ivy",91))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(mListOfPerson,{selectedPerson : Person -> listItemClicked(selectedPerson)})

    }

    private fun listItemClicked(person : Person){
        Toast.makeText(this@MainActivity,"Age : ${person.age}", Toast.LENGTH_SHORT).show()
    }
}