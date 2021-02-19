package com.example.barcode_scan


import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.barcode_scan.databinding.ActivityScannerBinding
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class ScannerActivity : BaseScannerActivity(), ZXingScannerView.ResultHandler {

    private val FLASH_STATE = "FLASH_STATE"

    private var mScannerView: ZXingScannerView? = null
    private var mFlash = false
    private var flashState = MutableLiveData<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        flashState.postValue(mFlash)
        super.onCreate(savedInstanceState)
        setupToolbar()
        val binding:ActivityScannerBinding = DataBindingUtil.setContentView(this,R.layout.activity_scanner)
        flashState.observe(this, Observer {
            binding.isTrue = it
        })
        val contentFrame = findViewById<View>(R.id.content_frame) as ViewGroup
        mScannerView = ZXingScannerView(this)
        contentFrame.addView(mScannerView)
    }

    override fun onResume() {
        super.onResume()
        mScannerView?.setResultHandler(this)
        mScannerView?.setAspectTolerance(0.2f)
        mScannerView?.startCamera()
        mScannerView?.flash = mFlash
        flashState.postValue(mFlash)
    }

    override fun onPause() {
        super.onPause()
        mScannerView?.stopCamera()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FLASH_STATE, mFlash)
    }

    override fun handleResult(rawResult: Result?) {
        Toast.makeText(
            this, "Contents = " + rawResult!!.text.toString() +
                    ", Format = " + rawResult!!.barcodeFormat.toString(), Toast.LENGTH_SHORT
        ).show()

        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.

        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        val handler = Handler()
        handler.postDelayed(
            Runnable { mScannerView?.resumeCameraPreview(this@ScannerActivity) },
            2000
        )
    }
    fun toggleFlash(v: View?) {
        mFlash = !mFlash
        flashState.postValue(mFlash)
        mScannerView!!.flash = mFlash
    }

}