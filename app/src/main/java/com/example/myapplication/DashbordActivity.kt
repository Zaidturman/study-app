package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import java.util.*



class DashbordActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var taskArrayList: ArrayList<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbord)

     val addbtn = findViewById<Button>(R.id.add)
        val btn = findViewById<Button>(R.id.button4)
        val progress = findViewById<Button>(R.id.progress)
        val done = findViewById<Button>(R.id.done)


        val aboutus = findViewById<ImageView>(R.id.aboutus)

        val name = intent.getStringExtra("name").toString()


        // الكود الخاص بالقائمه الجانبيه (menu)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val nav = findViewById<NavigationView>(R.id.nav)
        val viewmenu = findViewById<ImageView>(R.id.viewmenu)



        viewmenu.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

        nav.setNavigationItemSelectedListener { menuItem ->
            // اتخاذ إجراءات استجابة للعنصر المحدد في NavigationView
            when (menuItem.itemId) {
                R.id.dashbord -> {
                    val intent = Intent(this, DashbordActivity::class.java)
                    intent.putExtra("name",name)
                    startActivity(intent)

                    true
                }
                R.id.exams -> {
                    val intent = Intent(this, ExamActivity::class.java)
                    intent.putExtra("name",name)
                    startActivity(intent)
                    true
                }
                R.id.Tasks -> {
                    val intent = Intent(this, TaskActivity::class.java)
                    intent.putExtra("name",name)
                    startActivity(intent)
                    true
                }
                R.id.leacher -> {
                    val intent = Intent(this, LeacherActivity::class.java)
                    intent.putExtra("name",name)
                    startActivity(intent)
                    true
                }
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.putExtra("name",name)

                    startActivity(intent)
                    true
                }
                R.id.logout -> {
                    finish()
                    val intent = Intent(this, MainActivity::class.java)


                    startActivity(intent)
                    true
                }

                // اتخاذ إجراءات إضافية للعناصر الأخرى إذا لزم الأمر
                else -> false
            }
        }

        // end menu code


        val todaydate = findViewById<TextView>(R.id.todaydate)


        val welcome = findViewById<TextView>(R.id.welcome)
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]+1
        val day = calendar[Calendar.DAY_OF_MONTH]

        var d = "$day/$month/$year"

        todaydate.text = d
        taskRecyclerView = findViewById(R.id.tasklist1)
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.setHasFixedSize(true)

        taskArrayList = arrayListOf<Task>()
        val intent=intent

        val progressbtn = intent.getStringExtra("progress")

        getTaskData(name ,d)







        welcome.text = "Hello "+name+'!'

        addbtn.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("name" , name)
            startActivity(intent)
        }
        btn.setOnClickListener {
            val intent = Intent(this, TasklistActivity::class.java)
            intent.putExtra("name" , name)
            startActivity(intent)
        }
        progress.setOnClickListener {
            val intent = Intent(this, Progress::class.java)
            intent.putExtra("name" , name)

            intent.putExtra("progress" , progressbtn)
            startActivity(intent)
        }
        aboutus.setOnClickListener {
            val intent = Intent(this, AboutUs::class.java)
            intent.putExtra("name" , name)

            startActivity(intent)

        }
        done.setOnClickListener {
            val intent = Intent(this, DoneActivity::class.java)
            intent.putExtra("name" , name)

            startActivity(intent)
        }


        println("time"+d)









    }
    private fun getTaskData(n:String , d:String){

        dbref = FirebaseDatabase.getInstance().getReference("tasks").child(n)

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(taskSnapshot in snapshot.children){

                        val task = taskSnapshot.getValue(Task::class.java)
                        if (d.trim() == task?.etdate.toString().trim()) {

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