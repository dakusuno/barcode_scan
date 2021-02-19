package com.example.barcode_scan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private val ZXING_CAMERA_PERMISSION = 1
    private lateinit var mClss: Class<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun launchCameraFragment(v: View){
        launchActivity(ScannerActivity::class.java)
    }

    fun launchActivity(clss: Class<*>) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            val mClss = clss
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                ZXING_CAMERA_PERMISSION
            )
        } else {
            val intent = Intent(this, clss)
            startActivity(intent)
        }
    }
}