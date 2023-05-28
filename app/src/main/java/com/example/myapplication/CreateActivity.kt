package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createaccount)

        val login = findViewById<TextView>(R.id.textView3)
        val signup = findViewById<Button>(R.id.btnsign)

        val n =findViewById<EditText>(R.id.name)
        val e =findViewById<EditText>(R.id.email)
        val ph =findViewById<EditText>(R.id.phone)
        val p =findViewById<EditText>(R.id.password)






        signup.setOnClickListener {


            val user = User(n.text.toString().trim(),e.text.toString().trim(),ph.text.toString(),p.text.toString())
        println(user)
            val database = Firebase.database
            val myRef = database.reference

            myRef.child("users").push().setValue(user)

                .addOnSuccessListener {
                    // Data sent successfully
                    Toast.makeText(this, "Created Account Enjoy", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, DashbordActivity::class.java)
                    intent.putExtra("name" , n.text.toString().trim())
                    startActivity(intent)



                }
                .addOnFailureListener {
                    // Failed to send data

                    Toast.makeText(this, "Failed to send data to Firebase", Toast.LENGTH_SHORT).show()
                }






        }



        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}


