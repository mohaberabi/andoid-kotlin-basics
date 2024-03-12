package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val contacts: MutableList<Contact> = mutableListOf()
        for (i in 0 until 10) {
            contacts.add(
                Contact(
                    "Mohab Erabi",
                    bio = "I LOVE CODING",
                    imageRes = R.drawable.ic_launcher_foreground
                )
            )
        }
        val contactsRV: RecyclerView = findViewById(R.id.recyclerView)

        var adapter = ContactListAdapter(contacts)
        contactsRV.layoutManager = LinearLayoutManager(this)
        contactsRV.adapter = adapter

    }
}