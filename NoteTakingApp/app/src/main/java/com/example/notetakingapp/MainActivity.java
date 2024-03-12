package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    private Button addButton;
    private FloatingActionButton floatingActionButton;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVars();
    }

    private void initVars() {
        addButton = findViewById(R.id.addBtn);
        floatingActionButton = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.noteRecView);
    }
}