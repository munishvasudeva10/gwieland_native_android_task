package com.example.gweilandnativeandroidtask.modules.home.eshops

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gweilandnativeandroidtask.R
import com.example.gweilandnativeandroidtask.ui.custom_widgets.BottomNavigationBar
import com.example.gweilandnativeandroidtask.ui.custom_widgets.MainTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EShopsView(navController: NavHostController, vm: EShopsVM = hiltViewModel()) {
    Scaffold(
        topBar = {
            MainTopBar(stringResource(R.string.eshop))
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) {

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
            Text(text = stringResource(R.string.eshop_page), color = Color.Gray)
        }

    }

}


@Preview(showBackground = true)
@Composable
fun EShopsPreview() {

}