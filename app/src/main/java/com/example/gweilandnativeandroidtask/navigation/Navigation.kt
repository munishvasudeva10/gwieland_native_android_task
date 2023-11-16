package com.example.gweilandnativeandroidtask.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gweilandnativeandroidtask.modules.home.eshops.EShopsView
import com.example.gweilandnativeandroidtask.modules.home.exchange.ExchangeView
import com.example.gweilandnativeandroidtask.modules.home.launchpads.LaunchPadsView
import com.example.gweilandnativeandroidtask.modules.home.wallet.WalletView
import com.example.gweilandnativeandroidtask.modules.on_board.splash.LaunchView

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.LaunchScreen.route,
    )
    {

        composable(route = Screens.LaunchScreen.route) {
            LaunchView(navController)
        }

        composable(route = Screens.ExchangeScreen.route) {
            ExchangeView(navController)
        }

        composable(route = Screens.EShopScreen.route) {
            EShopsView(navController)
        }

        composable(route = Screens.LaunchPadsScreen.route) {
            LaunchPadsView(navController)
        }

        composable(route = Screens.WalletScreen.route) {
            WalletView(navController)
        }

    }
}