package com.example.myapplication

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import com.example.myapplication.R
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.etUsername)
        val password = findViewById<EditText>(R.id.etPassword)

        val database = Firebase.database
        val myRef = database.reference



        println("hi")



        val loginbtn = findViewById<Button>(R.id.btnLogin)
        val createaccount = findViewById<TextView>(R.id.textView3)

        loginbtn.setOnClickListener {




                val intent = Intent(this, DashbordActivity::class.java)

                myRef.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // قراءة البيانات من dataSnapshot
                        for (snapshot in dataSnapshot.children) {
                            val user = snapshot.getValue(User::class.java)
                            // التحقق من اسم المستخدم وكلمة المرور
                            if (user?.name == username.text.toString()
                                    .trim() && user?.password == password.text.toString()
                            ) {
                                // تحقق ناجح
                                Toast.makeText(
                                    this@LoginActivity,
                                    "تم تسجيل الدخول بنجاح",
                                    Toast.LENGTH_SHORT
                                ).show()
                                intent.putExtra("name", username.text.toString().trim())
                                startActivity(intent)
                                return
                            }
                        }
                        // التحقق غير ناجح
                        username.setHintTextColor(ColorStateList.valueOf(Color.RED))
                        password.setHintTextColor(ColorStateList.valueOf(Color.RED))
                        Toast.makeText(
                            this@LoginActivity,
                            "please enter valid user name or create account ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // فشل في قراءة القيمة
                        Log.e("Firebase", "فشل في قراءة القيمة.", error.toException())
                    }
                })
            }








        createaccount.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

    }
}

