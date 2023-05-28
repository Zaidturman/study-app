package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutUs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aboutus)

        val intent=intent

        val name = intent.getStringExtra("name").toString()
        val txt1 = findViewById<TextView>(R.id.textView4)

        txt1.text = "Hi $name"


    }
}
