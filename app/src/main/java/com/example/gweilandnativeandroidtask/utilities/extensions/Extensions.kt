package com.example.gweilandnativeandroidtask.utilities.extensions

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.google.gson.Gson

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
    }
}