package com.example.mediasharing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

   private  lateinit var image : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shareBtn : Button = findViewById(R.id.shareBtn)
         image   = findViewById(R.id.image)
        shareBtn.setOnClickListener{

            val galleryIntent  = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)


            startActivityForResult(galleryIntent,101)
        }


    }
    override fun onActivityResult(requestCode : Int, resultCode : Int  , data :Intent?){

        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101 && resultCode== RESULT_OK){
         image.setImageURI(data?.data)
        }
    }
}