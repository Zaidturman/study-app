package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.*

class DoneActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var taskArrayList: ArrayList<Task>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_done)

        val calendar = Calendar.getInstance()

        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]+1
        val day = calendar[Calendar.DAY_OF_MONTH]

        var d = "$day/$month/$year"


        taskRecyclerView = findViewById(R.id.tasklist)
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.setHasFixedSize(true)

        taskArrayList = arrayListOf<Task>()
        val intent=intent

        val name = intent.getStringExtra("name").toString()

        getTaskData(name ,d)









    }
    private fun getTaskData(n:String , d:String) {

        dbref = FirebaseDatabase.getInstance().getReference("tasks").child(n)

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (taskSnapshot in snapshot.children) {

                        val task = taskSnapshot.getValue(Task::class.java)
                        if (d.trim() > task?.etdate.toString().trim()) {

                            taskArrayList.add(task!!)
                        }

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