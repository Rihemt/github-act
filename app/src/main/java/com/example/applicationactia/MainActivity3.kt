package com.example.applicationactia

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class  MainActivity3 : AppCompatActivity(), View.OnClickListener {

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var imageView: ImageView
    private lateinit var saveButton: Button

    private var selectedImageBitmap: Bitmap? = null

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            isGranted ->
        if (isGranted) {
            dispatchTakePictureIntent()
        } else {
            Toast.makeText(this, "Permission refusée, impossible de prendre une photo", Toast.LENGTH_SHORT).show()
        }
    }

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            var imageBitmap = data?.extras?.get("data") as? Bitmap
            imageBitmap?.let {
                imageView.setImageBitmap(it)
                imageBitmap = it
            } ?: run {
                Toast.makeText(this, "Erreur lors de la capture de l'image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 1
        private const val REQUEST_IMAGE_CAPTURE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        email = findViewById(R.id.email)
        imageView = findViewById(R.id.imageView)
        saveButton = findViewById(R.id.saveButton)

        imageView.setOnClickListener {
            checkCameraPermissionAndDispatchTakePictureIntent()
        }

        saveButton.setOnClickListener {
            saveProfile()
        }
    }

    private fun checkCameraPermissionAndDispatchTakePictureIntent() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            dispatchTakePictureIntent()
        } else {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Toast.makeText(this, "La permission de la caméra est requise pour prendre une photo", Toast.LENGTH_SHORT).show()
        }
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureLauncher.launch(takePictureIntent)
    }

    private fun saveProfile() {
        val firstName = firstName.text.toString().trim()
        val lastName = lastName.text.toString().trim()
        val email = email.text.toString().trim()

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Profil enregistré avec succès", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onClick(view: View?) {
        // Gestion des clics des autres vues, si nécessaire
    }
}