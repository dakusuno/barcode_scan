package com.example.barcode_scan

import android.app.Application
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("isFlasOn")
fun flashLogo(view:ShapeableImageView,isTrue:Boolean){
    if(isTrue){
        Glide.with(view.context)
            .load(R.drawable.ic_baseline_flash_on_24)
            .into(view)
    }
    else{
        Glide.with(view.context)
            .load(R.drawable.ic_baseline_flash_off_24)
            .into(view)
    }
}