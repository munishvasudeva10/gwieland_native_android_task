package com.example.gweilandnativeandroidtask.ui.custom_widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gweilandnativeandroidtask.R

@Composable
fun SearchBar(searchText: MutableState<String> = mutableStateOf(""), hint:String="", isEnabled: Boolean=true, onAction: (action: String) -> Unit) {

    Surface(
        shape = RoundedCornerShape(25.dp),
        elevation = 0.dp,
        modifier = Modifier
    ) {

        TextField(
            modifier = Modifier
                .background(color = colorResource(id = R.color._F0F0F0)).defaultMinSize(minHeight = 40.dp)
                .clickable {
                    if (!isEnabled) {
                        onAction.invoke("click")
                    }
                },
            value = searchText.value,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (searchText.value.isNotEmpty()) {
                    IconButton(onClick = {
                        searchText.value = ""
                        onAction.invoke("")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            },
            onValueChange = {
                searchText.value = it
                onAction.invoke(searchText.value)
            },
            singleLine = true,
            placeholder = {
                Text(
                    text = hint, style = TextStyle(
                        fontSize = 13.sp,
                        color = colorResource(id = R.color._9D9D9D)
                    )
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor= Color.Transparent,
                backgroundColor = Color.Transparent
            ),
            shape = RoundedCornerShape(5.dp),
            textStyle = TextStyle(
                fontSize = 13.sp, fontWeight = FontWeight.Normal,
                color = Color.Black,
            ),
            enabled = isEnabled

        )
    }
}
