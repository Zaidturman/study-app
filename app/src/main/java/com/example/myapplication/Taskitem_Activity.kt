package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.ArrayList


class Taskitem_Activity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_item)

        val progressbtn = findViewById<CheckBox>(R.id.progressbtn)

        val intent=intent
        val name = intent.getStringExtra("name").toString()



        if(progressbtn.isChecked){
           val database = Firebase.database
           val myRef = database.reference

           myRef.child("tasks").child(name).child("status").push().setValue(true)
               .addOnSuccessListener {
                   // Data sent successfully
                   Toast.makeText(this, "Data sent ", Toast.LENGTH_SHORT).show()
               }
               .addOnFailureListener {
                   // Failed to send data
                   Toast.makeText(this, "Failed to send data ", Toast.LENGTH_SHORT).show()
               }
       }

    }
}
