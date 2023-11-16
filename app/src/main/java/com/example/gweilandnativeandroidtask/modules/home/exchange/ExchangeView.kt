package com.example.gweilandnativeandroidtask.modules.home.exchange

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.gweilandnativeandroidtask.R
import com.example.gweilandnativeandroidtask.models.CryptoModel
import com.example.gweilandnativeandroidtask.ui.custom_widgets.BottomNavigationBar
import com.example.gweilandnativeandroidtask.ui.custom_widgets.MainTopBar
import com.example.gweilandnativeandroidtask.ui.custom_widgets.SearchBar
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.imageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeView(
    navController: NavHostController,
    vm: ExchangeVM = hiltViewModel()
) {
    var showFilterSheet by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            MainTopBar(stringResource(R.string.exchanges))
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(
                    start = 26.dp,
                    end = 26.dp,
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.weight(1f)) {
                    SearchBar(
                        searchText = vm.searchText,
                        hint = stringResource(R.string.search_cryptocurrency),
                        isEnabled = true
                    ) {
                        //Search
                        vm.searchText.value = it
                        vm.searchCrypto()
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))
                OutlinedButton(
                    onClick = {
                        showFilterSheet = true
                    },
                    modifier = Modifier
                        .height(48.dp)
                        .wrapContentWidth(),  //avoid the oval shape
                    shape = RoundedCornerShape(28.dp),
                    border = BorderStroke(1.dp, colorResource(id = R.color._A7A7A7)),
                    contentPadding = PaddingValues(
                        horizontal = 0.dp,
                        vertical = 2.dp
                    ),  //avoid the little icon
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(id = R.color._A7A7A7))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = "filter"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = stringResource(R.string.filter),
                            color = colorResource(id = R.color._A7A7A7)
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.cryptocurrency),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontWeight = FontWeight(600),
                        color = colorResource(id = R.color._0A0B0B),
                    )
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = "NFT",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontWeight = FontWeight(600),
                        color = colorResource(id = R.color._A7A7A7),
                    )
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Box(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color._E0FADF),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .fillMaxWidth()
                    .height(131.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    ),
            ) {


                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_bitcoin),
                            contentDescription = "bitcoin"
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Column {
                            Text(
                                text = stringResource(R.string.btc),
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                                    fontWeight = FontWeight(700),
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = stringResource(R.string.bitcoin),
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF000000),
                                    textAlign = TextAlign.Center,
                                )
                            )
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "$55,000 USD",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                                    fontWeight = FontWeight(700),
                                    color = Color.Black,
                                    textAlign = TextAlign.Right,
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "+2.5%",
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                                    fontWeight = FontWeight(700),
                                    color = colorResource(id = R.color._00CE08),
                                    textAlign = TextAlign.Right,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Image(
                        painter = painterResource(id = R.drawable.slide_background),
                        contentDescription = "background"
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.top_cryptocurrencies),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontWeight = FontWeight(500),
                        color = colorResource(id = R.color._0B0B0B),

                        )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.view_all),
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontWeight = FontWeight(500),
                        color = colorResource(id = R.color._0B0B0B),
                        textAlign = TextAlign.Center,
                    )
                )
            }

            LazyColumn {
                itemsIndexed(vm.cryptoList.value) { index, item ->
                    CryptoItem(
                        item
                    ) {
                    }
                }

            }

        }


        if (showFilterSheet) {
            FilterBottomSheet(vm) {
                showFilterSheet = false
                vm.sortListIfRequired()
            }
        }
    }
}

@Composable
fun CryptoItem(item: CryptoModel, content: @Composable () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 18.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .size(46.dp),
            imageModel = { item.logo },
            imageOptions = ImageOptions(
                contentDescription = "image",
                contentScale = ContentScale.Crop, requestSize = IntSize(1024, 1024)
            ),
            component = imageComponent {
                +PlaceholderPlugin.Loading(painterResource(id = R.drawable.placeholder))
                +PlaceholderPlugin.Failure(painterResource(id = R.drawable.placeholder))
            }
        )

        Spacer(modifier = Modifier.width(9.dp))
        Column(modifier = Modifier.weight(1f)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.symbol ?: "",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontWeight = FontWeight(600),
                        color = Color.Black,
                    )

                )

                Spacer(modifier = Modifier.width(17.dp))

                Image(
                    painter = painterResource(
                        id = if ((item.quote?.USD?.percent_change_24h
                                ?: 0f) as Double >= 0
                        ) R.drawable.ic_graph_up else R.drawable.ic_graph_down
                    ),
                    contentDescription = "background"
                )
            }

            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.name ?: "",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                )
            )
        }

        Spacer(modifier = Modifier.width(9.dp))
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$${item.quote?.USD?.getRoundedPrice().toString()} USD",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontWeight = FontWeight(600),
                    color = Color.Black,

                    textAlign = TextAlign.Right,
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${if ((item.quote?.USD?.percent_change_24h ?: 0f) as Double >= 0) "+" else ""}${item.quote?.USD?.getPercentChange24() ?: 0} %",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontWeight = FontWeight(700),
                    color = colorResource(
                        id = if ((item.quote?.USD?.percent_change_24h
                                ?: 0f) as Double >= 0
                        ) R.color._00CE08 else R.color._FA2407
                    ),
                    textAlign = TextAlign.Right,
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(vm: ExchangeVM, function: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val selectedOption = remember { mutableStateOf(vm.selectedOption) }
    ModalBottomSheet(
        onDismissRequest = { function.invoke() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {

        LazyColumn {

            item {

                androidx.compose.material.Text(
                    text = "Filters", style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
                )
            }

            item {
                Column {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedOption.value == "price",
                            onClick = { selectedOption.value = "price" },
                            colors = RadioButtonColors(
                                selectedColor = colorResource(id = R.color.black),
                                unselectedColor = Color.Gray,
                                disabledSelectedColor = Color.LightGray,
                                disabledUnselectedColor = Color.LightGray,
                            )
                        )
                        Text(stringResource(R.string.sort_by_price))
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedOption.value == "change",
                            onClick = { selectedOption.value = "change" },
                            colors = RadioButtonColors(
                                selectedColor = colorResource(id = R.color.black),
                                unselectedColor = Color.Gray,
                                disabledSelectedColor = Color.LightGray,
                                disabledUnselectedColor = Color.LightGray,
                            )
                        )
                        Text(stringResource(R.string.sort_by_last_24_hours_change))
                    }
                }
            }

            item {
                Button(
                    onClick = {
                        vm.selectedOption = selectedOption.value
                        function.invoke()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(45.dp),
                    colors = androidx.compose.material.ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                ) {
                    androidx.compose.material.Text(
                        modifier=Modifier.background(Color.Black),
                        text = stringResource(R.string.apply), style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppDashBoardPreview() {

}