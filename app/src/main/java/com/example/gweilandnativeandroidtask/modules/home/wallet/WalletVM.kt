package com.example.gweilandnativeandroidtask.modules.home.wallet

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.example.gweilandnativeandroidtask.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WalletVM @Inject constructor(application: Application, savedStateHandle: SavedStateHandle): BaseVM(application,
    savedStateHandle
) {


}