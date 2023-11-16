package com.example.gweilandnativeandroidtask.navigation

sealed class Screens(val route: String) {
    object LaunchScreen : Screens("launch_screen")
    object ExchangeScreen : Screens("exchange_screen")
    object EShopScreen : Screens("e_shops_screen")
    object LaunchPadsScreen : Screens("launch_pads_screen")
    object WalletScreen : Screens("wallet_screen")
}