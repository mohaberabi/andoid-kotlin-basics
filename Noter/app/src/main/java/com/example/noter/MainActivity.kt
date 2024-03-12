package com.example.noter

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
//import com.example.noter.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var noteSaveButton: Button

    private lateinit var fab: FloatingActionButton
    private lateinit var recView: RecyclerView

    private lateinit var notLinearLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)

        noteSaveButton = findViewById(R.id.addBtn)

        fab = findViewById(R.id.fab)
        recView = findViewById(R.id.noteRecView)
        notLinearLayout = findViewById(R.id.linearNoteAdder)
        noteSaveButton.setOnClickListener {

            showNoteAdder()
        }

        fab.setOnClickListener {
            showNoteAdder()
        }

    }

    private fun showNoteAdder() {
        val dialog = Dialog(this@MainActivity)
        dialog.setContentView(R.layout.add_note_layout)

        val titleEdit: EditText = dialog.findViewById(R.id.editTitle)
        val titleDesc: EditText = dialog.findViewById(R.id.editSubTitle)
        val buttonAdd: Button = dialog.findViewById(R.id.saveButton)

        dialog.show()
    }
}