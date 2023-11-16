package com.example.gweilandnativeandroidtask.modules.on_board.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gweilandnativeandroidtask.R
import com.example.gweilandnativeandroidtask.utilities.extensions.popUpToTop

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchView(navController: NavHostController, vm: LaunchVM = hiltViewModel()) {


    Scaffold() {

        val context = LocalContext.current
        LaunchedEffect(key1 = context) {
            vm.navigationEvents.collect {
                navController.navigate(it.route) {
                    popUpToTop(navController)
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 20.dp,
                    end = 20.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_launcher_foreground
                ),
                tint=if(isSystemInDarkTheme()) Color.White else Color.Black,
                contentDescription = null
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun AppLaunchPreview() {
    LaunchView(rememberNavController())
}