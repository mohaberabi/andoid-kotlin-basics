package com.example.dataentry

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recView: RecyclerView
    private lateinit var contacts: MutableList<Contact>
    private lateinit var fab: FloatingActionButton
    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var previewImage: ImageView

    private lateinit var imgPickBtn: Button
    private lateinit var saveContactBtn: Button

    private lateinit var adapter: ContactListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        adapter = ContactListAdapter(contacts)
        recView = findViewById(R.id.rView)
        fab = findViewById(R.id.fab)
        contacts = mutableListOf()
        recView.adapter = adapter
        for (i in 0 until 16) {

            contacts.add(
                Contact(
                    "Mohab Erabi",
                    "+201111786667",
                    R.drawable.ic_launcher_background
                )
            )
        }


//        fab.setOnClickListener {
//            showContactAdder()
//        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK) {

            previewImage.visibility = View.VISIBLE
            previewImage.setImageURI(data?.data)

        }
    }


    private fun showContactAdder() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.app_dialog)

        imgPickBtn = dialog.findViewById(R.id.imgPickBtn)


        previewImage = dialog.findViewById(R.id.contatImg)

        saveContactBtn = dialog.findViewById(R.id.saveBtn)
        phoneEditText = dialog.findViewById(R.id.phoneEditText)
        nameEditText = dialog.findViewById(R.id.nameEditText)
        imgPickBtn.setOnClickListener {

            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 101)

        }
    }
}