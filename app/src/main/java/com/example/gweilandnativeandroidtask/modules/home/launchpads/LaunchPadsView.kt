package com.example.gweilandnativeandroidtask.modules.home.launchpads

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
import com.example.gweilandnativeandroidtask.modules.home.wallet.WalletVM
import com.example.gweilandnativeandroidtask.R
import com.example.gweilandnativeandroidtask.ui.custom_widgets.BottomNavigationBar
import com.example.gweilandnativeandroidtask.ui.custom_widgets.MainTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchPadsView(navController: NavHostController, vm: WalletVM = hiltViewModel()) {
    Scaffold(
        topBar = {
            MainTopBar(stringResource(R.string.launchpads))
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
            Text(text = stringResource(R.string.launchpads_page), color = Color.Gray)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LaunchPadsPreview() {

}