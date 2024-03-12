package com.example.drawer

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private lateinit var drawingView: DrawingView
    private lateinit var brushPickerButton: ImageButton
    private lateinit var pickedImage: ImageView
    private lateinit var undoButton: ImageButton
    private lateinit var saveButton: ImageButton
    private lateinit var colorsRow: LinearLayout
    private lateinit var choosedColorButton: ImageButton
    private lateinit var imagePickerButton: ImageButton
    private val openGalleryResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                pickedImage.setImageURI(result.data!!.data)
            }
        }
    private val permissions: ActivityResultLauncher<Array<String>> = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        map.entries.forEach {
            val name = it.key
            val granted = it.value

            if (granted) {
                val pickIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                openGalleryResult.launch(pickIntent)
            } else {
                if (name == Manifest.permission.READ_MEDIA_IMAGES) {
                    Toast.makeText(
                        this, "Permission is not granted ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pickedImage = findViewById(R.id.drawingViewBg)
        undoButton = findViewById(R.id.undoButton)
        imagePickerButton = findViewById(R.id.imagePickerButton)
        colorsRow = findViewById(R.id.colorsRow)
        drawingView = findViewById(R.id.drawingView)
        choosedColorButton = colorsRow[0] as ImageButton
        choosedColorButton.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.selected)
        )
        drawingView.setSizeForBrush(20.toFloat())


        brushPickerButton = findViewById(R.id.brushPickerBtn)

        brushPickerButton.setOnClickListener {
            showBrushPickerDialog()
        }
        colorsRow.forEach {
            it.setOnClickListener { color ->
                chooseColor(color)
            }
        }
        imagePickerButton.setOnClickListener {
            requestStoragePermission()
        }
        undoButton.setOnClickListener {
            undo()
        }

        saveButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {

            lifecycleScope.launch {

                val drawingView: FrameLayout = findViewById(R.id.drawingViewFrame)
                val bitmap = getBitMapFromView(drawingView)
                val filePath = saveImageFromBitMap(bitmap)

            }
        }

    }

    private fun undo() {
        drawingView.undo()
    }

    private fun showBrushPickerDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialoog_brush_size)
        brushDialog.setTitle("Select Brush Size")
        val small = brushDialog.findViewById<ImageButton>(R.id.brushSmallBtn)
        val medium = brushDialog.findViewById<ImageButton>(R.id.brushMediumBtn)
        val large = brushDialog.findViewById<ImageButton>(R.id.brushLargeBtn)
        small.setOnClickListener {
            drawingView.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
        medium.setOnClickListener {
            drawingView.setSizeForBrush(16.toFloat())
            brushDialog.dismiss()
        }
        large.setOnClickListener {
            drawingView.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }

        brushDialog.show()
    }

    private fun chooseColor(view: View) {

        if (view !== choosedColorButton) {
            val imageButton = view as ImageButton
            val tag = imageButton.tag.toString()

            drawingView.setColor(tag)

            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.selected)
            )

            choosedColorButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_normal)
            )

            choosedColorButton = view
        }

    }


    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_MEDIA_IMAGES
            )
        ) {
            showAlertDialog("We Need To Access The Gallery", "To Allow for drawing in the images")
        } else {
            permissions.launch(arrayOf(Manifest.permission.READ_MEDIA_IMAGES))
        }
    }

    private fun showAlertDialog(title: String, message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setTitle(title)
            .setPositiveButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }


    private fun getBitMapFromView(view: View): Bitmap {


        val resultBitMap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(resultBitMap)

        val bgDrawble = view.background

        if (bgDrawble != null) {
            bgDrawble.draw(canvas)
        } else {
            canvas.drawColor(Color.WHITE)
        }

        view.draw(canvas)
        return resultBitMap
    }


    private suspend fun saveImageFromBitMap(bitmap: Bitmap?): String {
        var result = ""
        withContext(Dispatchers.IO) {

            if (bitmap != null) {
                try {
                    val bytes = ByteArrayOutputStream()

                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, bytes)

                    val file =
                        File(
                            externalCacheDir?.absoluteFile.toString() + File.separator
                                    + "drawerApp_" + System.currentTimeMillis() / 1000 + ".png"
                        )

                    val fileOutPutStream = FileOutputStream(file)
                    fileOutPutStream.write(bytes.toByteArray())
                    fileOutPutStream.close()

                    result = file.absoluteFile.toString()

                    runOnUiThread {
                        if (result.isNotEmpty()) {
                            Toast.makeText(
                                this@MainActivity,
                                "File Saved :${result}",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Something Went Wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (e: Exception) {

                    result = ""
                }
            }

        }
        return result

    }
}


//    private val galleryPickerResult: ActivityResultLauncher<String> = registerForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) { isGranted ->
//        if (isGranted) {
//            Toast.makeText(this, "Granted", Toast.LENGTH_LONG).show()
//        } else {
//            Toast.makeText(this, "Denied", Toast.LENGTH_LONG).show()
//        }
//    }

//    private val cameraAndLocation: ActivityResultLauncher<Array<String>> =
//        registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) { map ->
//
//            map.entries.forEach {
//
//
//                val key = it.key
//                val isGranted = it.value
//
//                if (isGranted) {
//                    if (key == Manifest.permission.CAMERA) {
//                        Toast.makeText(this, " Camera okay", Toast.LENGTH_LONG).show()
//
//                    } else {
//                        Toast.makeText(this, " Location okay", Toast.LENGTH_LONG).show()
//                    }
//                } else {
//                    if (key == Manifest.permission.CAMERA) {
//                        Toast.makeText(this, " Camera not okay", Toast.LENGTH_LONG).show()
//
//                    } else {
//                        Toast.makeText(this, " Location  not okay", Toast.LENGTH_LONG).show()
//                    }
//
//                }
//
//            }
//
//        }//
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
////                shouldShowRequestPermissionRationale(
////                    Manifest.permission.READ_EXTERNAL_STORAGE
////                )
////            ) {
////
////                showAlertDialog(
////                    "Requires the gallery permissions to pick the image to draw on it ",
////                    "can not be shown or used "
////                )
////            } else {
////
////
//////                galleryPickerResult.launch(Manifest.permission.CAMERA)
////            }