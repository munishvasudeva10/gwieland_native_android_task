package com.example.gweilandnativeandroidtask.modules.on_board.splash

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.gweilandnativeandroidtask.base.BaseVM
import com.example.gweilandnativeandroidtask.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchVM @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
) : BaseVM(application, savedStateHandle) {


    init {
        navigateWithTimer()
    }

    private fun navigateWithTimer() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModelScope.launch(Dispatchers.Main) {
                navigationEventChannel.send(Screens.ExchangeScreen)
            }
        }, 2000)
    }
}