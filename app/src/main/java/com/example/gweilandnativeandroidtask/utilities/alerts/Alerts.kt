package com.example.gweilandnativeandroidtask.utilities.alerts

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

object Alerts {


    /**Alert*/
    fun showMessage(context: Context, message: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

}