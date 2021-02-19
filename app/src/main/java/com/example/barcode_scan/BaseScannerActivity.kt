package com.example.barcode_scan

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity


open class BaseScannerActivity:AppCompatActivity() {
    fun setupToolbar() {
        val toolbar =   findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab: ActionBar? = supportActionBar
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item);
    }
}