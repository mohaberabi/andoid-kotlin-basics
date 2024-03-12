package com.example.happyplaces

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.happyplaces.databinding.ActivityAddPlaceBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddPlaceActivity : AppCompatActivity(), View.OnClickListener {

    private var binding: ActivityAddPlaceBinding? = null

    private val calender = Calendar.getInstance()

    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPlaceBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
        dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calender.set(Calendar.YEAR, year)
            calender.set(Calendar.MONTH, month)
            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateTime()
        }
        binding?.etDate?.setOnClickListener(this)
        binding?.tvAddImage?.setOnClickListener(this)
        binding?.imagePlaceHolder?.setOnClickListener(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(view: View?) {


        when (view!!.id) {
            R.id.et_date -> {
                DatePickerDialog(
                    this@AddPlaceActivity,
                    dateSetListener,
                    calender.get(Calendar.YEAR),
                    calender.get(Calendar.MONTH),
                    calender.get(Calendar.DAY_OF_MONTH),
                ).show()
            }

            R.id.imagePlaceHolder -> {
                val dialog = AlertDialog.Builder(this@AddPlaceActivity)
                dialog.setTitle("Select image")
                val picDialogsItems = arrayOf("Image", "Camera")
                dialog.setItems(picDialogsItems) { dialog, index ->

                    when (index) {
                        0 -> {
                            selectPhotoFromGallery()
                        }

                        else -> {}
                    }

                }
                dialog.show()
            }
        }

    }


    private fun selectPhotoFromGallery() {

        Dexter.withContext(this@AddPlaceActivity)
            .withPermissions(Manifest.permission.READ_MEDIA_IMAGES)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {


                    if (report != null) {
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(this@AddPlaceActivity, "GRANTED ALL", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {

                    Toast.makeText(
                        this@AddPlaceActivity,
                        "PLEASE GRANT TO ENJOY THIS USELESS DUMMY APP",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }


            }).check()
    }

    private fun updateDateTime() {
        val format = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        binding?.etDate?.setText(sdf.format(calender.time).toString())
    }
}