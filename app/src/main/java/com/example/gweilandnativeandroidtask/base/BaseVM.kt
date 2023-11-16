package com.example.gweilandnativeandroidtask.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.gweilandnativeandroidtask.navigation.Screens
import com.example.gweilandnativeandroidtask.utilities.alerts.Alerts
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

open class BaseVM constructor(
    application: Application,
    val savedStateHandle: SavedStateHandle
) :
    AndroidViewModel(application) {

    val navigationEventChannel= Channel<Screens> {  }
    val navigationEvents=navigationEventChannel.receiveAsFlow()



    fun alert(message: Any) {
        Alerts.showMessage(
            getApplication(), when (message) {
                is String -> {
                    message
                }

                is Int -> {
                    (getApplication() as Context).resources.getString(
                        message
                    )
                }

                else -> {
                    message.toString()
                }
            }
        )
    }
}