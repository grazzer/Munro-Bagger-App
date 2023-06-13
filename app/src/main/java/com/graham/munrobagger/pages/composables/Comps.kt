package com.graham.munrobagger.pages.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun PageTitle(title:String){
    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
    ){
        Text(text = title,
            fontSize = 22.sp,
            maxLines = 1,
            modifier = Modifier
                .align(Center)
        )
    }
    Spacer(modifier = Modifier
        .height(15.dp))
}

@Composable
fun TopSection(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopStart,
//        modifier = modifier
//            .background(
//                Brush.verticalGradient(
//                    listOf(
//                        Color.LightGray,
//                        Color.Transparent
//                    )
//                )
//            )
    ) {
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier
                .size(36.dp)
                .offset(10.dp, 16.dp)
                .clickable {
                    navController.popBackStack()
                }
        )

    }
}