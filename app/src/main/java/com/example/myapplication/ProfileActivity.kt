package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.annotations.Nullable
import java.io.IOException

class ProfileActivity : AppCompatActivity() {


    private lateinit var dbref: DatabaseReference
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var taskArrayList: ArrayList<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val intent = intent


        val name = intent.getStringExtra("name").toString()


        var email = ""

        val profilename = findViewById<TextView>(R.id.profile_name)
        val profileemail = findViewById<TextView>(R.id.profile_email)

        profilename.text = name.toString()
        val profileImageView = findViewById<ImageView>(R.id.profile_image);

        val editProfileButton = findViewById<Button>(R.id.edit_profile_button);

         object {
            private val PICK_IMAGE_REQUEST = 1
        }


         var selectedImageUri: Uri? = null



        profileImageView.setOnClickListener {
                openFilePicker()
            }
        }
    val PICK_IMAGE_REQUEST = 0
        private fun openFilePicker() {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_OPEN_DOCUMENT
            intent.addCategory(Intent.CATEGORY_OPENABLE)

            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_REQUEST
            )
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
                var selectedImageUri = data.data
                try {
                    val bitmap: Bitmap =
                        MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                    val profileImageView = findViewById<ImageView>(R.id.profile_image);
                    profileImageView.setImageBitmap(bitmap)

                    // Perform image upload here using the selectedImageUri
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }


