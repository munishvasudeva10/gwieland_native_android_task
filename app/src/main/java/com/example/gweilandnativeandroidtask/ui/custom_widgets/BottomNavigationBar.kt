package com.example.gweilandnativeandroidtask.ui.custom_widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gweilandnativeandroidtask.R
import com.example.gweilandnativeandroidtask.navigation.Screens

sealed class BottomNavigationItemm(
    val route: String? = null,
    @StringRes val titleResId: Int? = null,
    val icon: Int? = null
) {
    object Exchange : BottomNavigationItemm(
        route = Screens.ExchangeScreen.route,
        titleResId = R.string.exchange,
        icon = R.drawable.ic_exchange
    )

    object EShops : BottomNavigationItemm(
        route = Screens.EShopScreen.route,
        titleResId = R.string.eshop,
        icon = R.drawable.baseline_shopping_cart_checkout_24
    )

    object Globe : BottomNavigationItemm(
        route = null,
        titleResId = null,
        icon = null
    )

    object LaunchPad : BottomNavigationItemm(
        route = Screens.LaunchPadsScreen.route,
        titleResId = R.string.launchpads,
        icon = R.drawable.ic_launch_pads
    )

    object Wallet : BottomNavigationItemm(
        route = Screens.WalletScreen.route,
        titleResId = R.string.wallet,
        icon = R.drawable.ic_wallet
    )

}


@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        BottomNavigationItemm.EShops,
        BottomNavigationItemm.Exchange,
        BottomNavigationItemm.Globe,
        BottomNavigationItemm.LaunchPad,
        BottomNavigationItemm.Wallet
    )

    BottomNavigation(
        modifier = Modifier
            .padding(bottom = 20.dp, start = 12.dp, end = 12.dp)
            .fillMaxWidth()
            .height(70.dp)
            .clip(
                RoundedCornerShape(25.dp)
            ),
        backgroundColor = Color.Black,
        contentColor = MaterialTheme.colors.primary,
        elevation = 8.dp,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->


            when (item) {
                is BottomNavigationItemm.Globe -> {
                    Image(
                        modifier=Modifier.padding(10.dp),
                        painter = painterResource(id = R.drawable.metaverse),
                        contentDescription = "globe",
                    )
                }

                else -> {
                    BottomNavigationItem(
                        modifier = Modifier,
                        icon = {
                            Icon(
                                painterResource(id = item.icon ?: 0),
                                contentDescription = stringResource(id = item.titleResId ?: 0),
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = item.titleResId ?: 0), style = TextStyle(
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Normal
                                ),
                                maxLines = 1
                            )
                        },
                        selected = currentRoute == item.route,

                        selectedContentColor = Color.White,
                        unselectedContentColor = colorResource(id = R.color.text_grey),

                        onClick = {
                            navController.navigate(item.route ?: "") {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                // Avoid multiple copies of the same destination when re-selecting the same item
                                launchSingleTop = true
                                // Restore state when re-selecting a previously selected item
                                restoreState = true
                            }
                        },

                        )


                }
            }
        }


    }

}