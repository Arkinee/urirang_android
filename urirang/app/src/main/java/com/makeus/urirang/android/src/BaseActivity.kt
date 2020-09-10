package com.makeus.urirang.android.src

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.makeus.urirang.android.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    lateinit var mProgressDialog:AppCompatDialog

    fun showCustomToastShort(message: String?) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun showCustomToastLong(message: String?) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = AppCompatDialog(this);
            mProgressDialog!!.setMessage(getString(R.string.loading))
            mProgressDialog!!.setCancelable(false)
            mProgressDialog.isIndeterminate = true
        }
        mProgressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    public override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }
}