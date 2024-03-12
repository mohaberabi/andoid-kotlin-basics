package com.example.happyplaces

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.happyplaces.databinding.ActivityCameraBinding

class CameraActivity : AppCompatActivity() {


    companion object {
        private const val cameraPermissionCode = 1
        private const val cameraRequestCode = 2

    }

    private var binding: ActivityCameraBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)

        setContentView(binding?.root)



        binding?.cameraButton?.setOnClickListener {

            val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

            if (permission == PackageManager.PERMISSION_GRANTED) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)


                startActivityForResult(cameraIntent, cameraRequestCode)
            } else {

                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CAMERA),
                    cameraPermissionCode
                )

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == cameraPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, cameraRequestCode)
            } else {
                Toast.makeText(this@CameraActivity, "FUCK U", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == cameraRequestCode) {
                val bitmap: Bitmap = data!!.extras!!.get("data") as Bitmap
                binding?.image?.setImageBitmap(bitmap)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}