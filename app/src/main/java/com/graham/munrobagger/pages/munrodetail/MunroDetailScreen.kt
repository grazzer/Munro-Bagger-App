package com.graham.munrobagger.pages.munrodetail

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.graham.munrobagger.R
import com.graham.munrobagger.data.remote.responses.Munro
import com.graham.munrobagger.pages.munrolist.MunroEntry
import com.graham.munrobagger.util.Resource

@Composable
fun MunroDetailScreen(
    munroNumber: String,
    navController: NavController,
    viewModel: MunroDetailViewModel = hiltViewModel()
) {
//    val munroInfo = produceState<Resource<Munro>>(initialValue = Resource.Loading()) {
//        value = viewModel.getMunroInfo(munroNumber)
//    }.value

    viewModel.getMunroInfo(munroNumber)
    val munroInfo = viewModel.munroinfo.value


//    viewModel.getMunro(munroNumber)
//    viewModel.getParentMunro(viewModel.munro.value.data?.Parent_name_SMC .toString())
//    val munroInfo = produceState<Resource<Munro>>(initialValue = Resource.Loading()) {
//        value = viewModel.getMunroInfo(munroNumber)
//    }.value

//    val HillBaggingUrl = buildAnnotatedString {
//        append(munroInfo.data?.Hill_bagging.toString())
//        pushStringAnnotation(
//            tag = "url",
//            annotation = "www.google.com")
//        pop()
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 16.dp)
    ) {
//        MunroDetailTopSection(
//            navController = navController,
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(0.2f)
//                .align(Alignment.TopCenter)
//        )
        MunroDetailStateWrapper(
//            url = HillBaggingUrl,
            munroInfo = munroInfo,
            modifier = Modifier
                .fillMaxSize()
                .align(Center),
            loadingModifier = Modifier
                .align(Center),
            viewModel = viewModel,
            navController = navController
        )
//        Box(
//            contentAlignment = Alignment.TopCenter,
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            if (munroInfo is Resource.Success) {
//                Image(
//                    painter = painterResource(id = com.graham.munrobagger.R.drawable.munro_icon),
//                    contentDescription = "Munro Icon",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(ImageSize)
//                        .offset(y = topPadding)
//                )
//            }
//        }

        MunroDetailTopSection(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
fun MunroDetailTopSection(
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


@Composable
fun MunroDetailStateWrapper(
    munroInfo: Resource<Munro>,
//    url: AnnotatedString,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
    viewModel: MunroDetailViewModel,
    navController: NavController
) {
    when (munroInfo) {
        is Resource.Success -> {
            MunroDetails(munroInfo, modifier, viewModel, navController)
        }
        is Resource.Error -> {
            Text(
                text = munroInfo.message!!,
                color = Color.Red,
                modifier = loadingModifier
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

@Composable
fun MunroDetails(
    munroInfo: Resource<Munro>,
//    url: AnnotatedString,
    modifier: Modifier = Modifier,
    viewModel: MunroDetailViewModel,
    navController: NavController
){
    Column(modifier = modifier
//                .verticalScroll(rememberScrollState())
    ) {

        // replacement for image
        Box( modifier = Modifier
            .aspectRatio(1.5f)
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
        )
//                Image(
//                    painter = painterResource(id = com.graham.munrobagger.R.drawable.munro_icon),
//                    contentDescription = "Munro Icon",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .aspectRatio(1.5f)
//                        .fillMaxWidth()
//                )
        Column(
            modifier = Modifier
                .offset(y = -16.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(15.dp))
                .background(color = MaterialTheme.colors.background)
                .padding(20.dp)
        )
        {
            Text(
                text = munroInfo.data?.Name.toString(),
                fontSize = 32.sp,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row{
                Icon(
                    painter = painterResource(R.drawable.ic_location_marker),
                    contentDescription = "#",
                    modifier = Modifier
                        .height(17.dp)
                        .align(CenterVertically)
                )
                Spacer(modifier = Modifier
                    .width(5.dp))
                Text(
                    text = munroInfo.data?.County.toString(),
                    fontSize = 16.sp,
                    maxLines = 1
                )
            }

            hightSection(
                munroInfo = munroInfo
            )

            climbedSection(
                munroInfo = munroInfo,
                navController = navController
            )

//            ParentMunro(
//                munroInfo = munroInfo,
//                viewModel = viewModel,
//                navController = navController
//            )


            mapSection(munroInfo = munroInfo)
            aboutSection(munroInfo)
        }
    }
}

@Composable
fun hightSection(
    munroInfo: Resource<Munro>
){
    Spacer(modifier = Modifier
        .height(16.dp))

    Box(modifier = Modifier
        .shadow(5.dp, RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .background(
            MaterialTheme.colors.onPrimary
        )
        .padding(10.dp)
    ){
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                StatsRow(
                    icon = R.drawable.ic_mountain,
                    text = munroInfo.data?.Drop.toString() + " m"
                )
            }
            Box(
                modifier = Modifier
                    .weight(0.25f)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                StatsRow(
                    icon = R.drawable.ic_sea,
                    text = munroInfo.data?.Metres.toString() + " m"
                )
            }
        }
    }
}

@Composable
fun mapSection(
    munroInfo: Resource<Munro>
){
    sectionHeading("Map")
}

@Composable
fun climbedSection(
    munroInfo: Resource<Munro>,
    navController: NavController
){
    sectionHeading("Climbed")

    Box(modifier = Modifier
        .shadow(5.dp, RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .background(
            MaterialTheme.colors.onPrimary
        )
        .padding(10.dp)
    ){
        Column() {
            FloatingActionButton(onClick = {  navController.navigate("AddClimbScreen/{1}")}) {
                
            }
//            Row(
//                Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                ) {
//                    Column() {
//                        StatsRow(icon = R.drawable.ic_date, text = "1/2/2023")
//                        Spacer(modifier = Modifier.height(10.dp))
//                        StatsRow(icon = R.drawable.ic_distance, text = "100 m")
//                    }
//                }
//                Box(
//                    modifier = Modifier
//                        .weight(0.25f)
//                )
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                ) {
//                    Column() {
//                        StatsRow(icon = R.drawable.ic_weather, text = "Sunny")
//                        Spacer(modifier = Modifier.height(10.dp))
//                        StatsRow(icon = R.drawable.ic_time, text = "1:30")
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(10.dp))
//            StatsRow(icon = R.drawable.ic_walkers, text = "graham, sam")
        }
    }
    Spacer(modifier = Modifier
        .height(16.dp))
}

@Composable
fun aboutSection(
    munroInfo: Resource<Munro>
){
    sectionHeading("About")

    Row(Modifier.fillMaxWidth(),
    ){
        Text(
            text = "Classification: ",
            fontSize = 12.sp,
            maxLines = 1,
        )
        Text(
            text = munroInfo.data?.Classification.toString(),
            fontSize = 12.sp,
            maxLines = 1,
        )
    }
    Row(Modifier.fillMaxWidth(),
    ){
        Text(
            text = "Summit feature: ",
            fontSize = 12.sp,
            maxLines = 1,
        )
        Text(
            text = munroInfo.data?.Feature.toString(),
            fontSize = 12.sp,
            maxLines = 1,
        )
    }
    Row(Modifier.fillMaxWidth(),
        ){
            Text(
            text = "Ordnance Survey: ",
            fontSize = 12.sp,
            maxLines = 1,
        )
        Text(
            text = "X = " + munroInfo.data?.Xcoord.toString() + "  Y = " + munroInfo.data?.Ycoord.toString(),
            fontSize = 12.sp,
            maxLines = 1
        )
    }
    Row(Modifier.fillMaxWidth(),
    ){
        Text(
            text = "Hill Bagging page: ",
            fontSize = 12.sp,
            maxLines = 1,
        )
        Text(
            text = munroInfo.data?.Hill_bagging.toString(),
            fontSize = 12.sp,
            maxLines = 1
        )
//        ClickableText(text = url, onClick = url.getStringAnnotations(tag = "policy", start = 0, end = 2).firstOrNull())
    }
}


@Composable
fun ParentMunro(
    munroInfo : Resource<Munro>,
    viewModel: MunroDetailViewModel,
    navController: NavController
){
    if(munroInfo.data?.Parent_SMC.toString() != "null") {
        sectionHeading("Parent")

        val parentMunroInfo = viewModel.parentMunro.value!!

        MunroEntry(
            entry = parentMunroInfo,
            navController = navController
        )
        Spacer(
            modifier = Modifier
                .height(6.dp)
        )
    }
}


@Composable
fun sectionHeading(
    title: String
){
    Spacer(modifier = Modifier
        .height(16.dp))
    Text(
        text =  title,
        fontSize = 18.sp,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
    )
    Spacer(modifier = Modifier
        .height(8.dp))
}


@Composable
fun StatsRow(
    icon: Int,
    text: String
){
    Row {
        Box(modifier = Modifier
            .height(40.dp)
//                            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                MaterialTheme.colors.primary
            )){
            Icon(
                painter = painterResource(icon),
                contentDescription = "Height",
                modifier = Modifier
                    .height(25.dp)
                    .align(Center)
            )
        }
        Spacer(modifier = Modifier
            .width(15.dp)
        )
        Text(
            text = text,
            fontSize = 20.sp,
            maxLines = 1,
            modifier = Modifier
                .align(CenterVertically)
        )
    }

}




