package com.example.applicationactia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.*
import android.provider.Settings




class MainActivity2 : AppCompatActivity() , View.OnClickListener{
    private lateinit var cam: TextView
    private lateinit var wifi: TextView
    private lateinit var map: TextView
    private lateinit var pro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

         cam=  findViewById(R.id.cam)
         wifi= findViewById(R.id.wifi)
         map= findViewById(R.id.map)
         pro= findViewById(R.id.pro)

        cam.setOnClickListener(this)
        wifi.setOnClickListener(this)
        map.setOnClickListener(this)
        pro.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.cam-> openCamera()
            R.id.wifi -> openWiFiSettings()
            R.id.map-> openMap()
            R.id.pro-> openEditProfile()
        }
    }

    private fun openCamera() {
        // Ouvrir l'appareil photo
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    private fun openWiFiSettings() {
        // Ouvrir les paramètres WiFi
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        startActivity(intent)
    }

    private fun openMap() {
        // Ouvrir la carte
        val locationUri = Uri.parse("geo:0,0?q=latitude,longitude(label)")
        val intent = Intent(Intent.ACTION_VIEW, locationUri)
        startActivity(intent)
    }

    private fun openEditProfile() {

        // Ouvrir l'écran de modification du profil
        val intent = Intent(this,MainActivity3::class.java)
        startActivity(intent)
    }
}
