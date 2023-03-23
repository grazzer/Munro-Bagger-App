package com.graham.munrobagger.munrodetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.graham.munrobagger.data.remote.responses.Munro
import com.graham.munrobagger.util.Resource
import javax.annotation.meta.When


@Composable
fun MunroDetailScreen(
    munroNumber: String,
    navController: NavController,
    topPadding: Dp = 20.dp,
    ImageSize: Dp = 350.dp,
    viewModel: MunroDetailViewModel = hiltViewModel()
) {
    val munroInfo = produceState<Resource<Munro>>(initialValue = Resource.Loading()) {
        value = viewModel.getMunroInfo(munroNumber)
    }.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(bottom = 16.dp)
    ) {
        MunroDetailTopSection(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopCenter)
        )
        MunroDetailStateWrapper(
            munroInfo = munroInfo,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding + ImageSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.surface)
                .padding(16.dp)
                .align(Alignment.Center),
            loadingModifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
                .padding(
                    top = topPadding + ImageSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        )
        Box(
            contentAlignment = Alignment.TopCenter,

            modifier = Modifier
                .fillMaxSize()
        ) {
            if (munroInfo is Resource.Success) {

//                Image(
//                    painter = painterResource(),
//                    contentDescription = "Munro Icon",
//                    contentScale = ContentScale.Crop
//                )
                Text(text = "# " + munroInfo.data?.Number.toString())
            }
        }
    }
}

@Composable
fun MunroDetailTopSection(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.LightGray,
                        Color.Transparent
                    )
                )
            )
    ) {
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .offset(16.dp, 16.dp)
                .clickable {
                    navController.popBackStack()
                }
        )

    }
}

@Composable
fun MunroDetailStateWrapper(
    munroInfo: Resource<Munro>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when (munroInfo) {
        is Resource.Success -> {
//                Box(contentAlignment = Alignment.TopCenter,
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                if(munroInfo is Resource.Success){
//                    Text(text = "# " + munroInfo.data?.Number.toString())
//                }
//            }
        }
        is Resource.Error -> {
            Text(
                text = munroInfo.message!!,
                color = Color.Red,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = loadingModifier
            )
        }
    }

}