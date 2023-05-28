package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        var p =""

        val spinner = findViewById<Spinner>(R.id.spinner)


        val options = resources.getStringArray(R.array.gender_options)


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter



        val etTitle = findViewById<EditText>(R.id.editTextTextPersonName)
        val etDate = findViewById<DatePicker>(R.id.datepicker)
        val etTime = findViewById<TimePicker>(R.id.timepicer)

        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        etDate.init(year, month, day, null);


        val intent=intent
        val addbtn = findViewById<Button>(R.id.button2)



        addbtn.setOnClickListener {
            val year: Int = etDate.getYear()
            val month: Int = etDate.getMonth() + 1 // يجب إضافة 1 للحصول على الشهر الفعلي
            val day: Int = etDate.getDayOfMonth()


            val hour: Int = etTime.hour
            val min: Int = etTime.minute

            var d = "$day/$month/$year"
            var t = "$hour:$min"

            val selectedOption = spinner.selectedItem.toString()
            val status = intent.getStringExtra("status").toString()


            val task = Task(selectedOption,etTitle.text.toString().trim(),d,t,status)



            val name = intent.getStringExtra("name").toString()


            val database = Firebase.database
            val myRef = database.reference

                myRef.child("tasks").child(name).push().setValue(task)
                    .addOnSuccessListener {
                        // Data sent successfully
                        Toast.makeText(this, "Data sent ", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        // Failed to send data
                        Toast.makeText(this, "Failed to send data ", Toast.LENGTH_SHORT).show()
                    }


            val intent = Intent(this, DashbordActivity::class.java)
            intent.putExtra("name" , name)

            startActivity(intent)


        }

    }
}