package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*


class TasklistActivity : AppCompatActivity(){

    private lateinit var dbref : DatabaseReference
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var taskArrayList: ArrayList<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasklist)


        taskRecyclerView = findViewById(R.id.tasklist)
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.setHasFixedSize(true)

        taskArrayList = arrayListOf<Task>()

        val intent=intent

        val name = intent.getStringExtra("name").toString()
        getTaskData(name)

        println(name)






    }

    private fun getTaskData(n:String){

        dbref = FirebaseDatabase.getInstance().getReference("tasks").child(n)

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(taskSnapshot in snapshot.children){

                        val task = taskSnapshot.getValue(Task::class.java)

                        taskArrayList.add(task!!)

                    }
                    taskRecyclerView.adapter = MyAdapter(taskArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }



}