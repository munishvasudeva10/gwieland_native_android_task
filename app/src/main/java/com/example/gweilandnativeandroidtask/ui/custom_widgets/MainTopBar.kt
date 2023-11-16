package com.example.gweilandnativeandroidtask.ui.custom_widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gweilandnativeandroidtask.R


@Composable
fun MainTopBar(title:String) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 46.dp, start = 26.dp, end = 26.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier=Modifier.weight(1f),
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF0B0B0B),
                    )
            )
            Spacer(Modifier.width(20.dp))
            Icon(painter = painterResource(id = R.drawable.ic_notification) , contentDescription ="notification" )
            Spacer(Modifier.width(15.dp))
            Icon(painter = painterResource(id = R.drawable.ic_settings) , contentDescription ="notification" )
        }
    }

}