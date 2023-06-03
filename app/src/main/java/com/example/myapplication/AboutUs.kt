package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutUs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aboutus)

        val intent=intent

        val name = intent.getStringExtra("name").toString()
        val txt1 = findViewById<TextView>(R.id.textView4)
        val back = findViewById<ImageView>(R.id.imageView2)

        back.setOnClickListener {
            val intent = Intent(this, DashbordActivity::class.java)
            intent.putExtra("name" , name)
            startActivity(intent)
        }

        txt1.text = "Hi $name"


    }
}
