package com.example.gweilandnativeandroidtask.modules.home.eshops

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.example.gweilandnativeandroidtask.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EShopsVM @Inject constructor(application: Application, savedStateHandle: SavedStateHandle): BaseVM(application,
    savedStateHandle
) {


}