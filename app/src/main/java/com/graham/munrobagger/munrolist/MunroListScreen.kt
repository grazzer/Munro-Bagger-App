package com.graham.munrobagger.munrolist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.graham.munrobagger.data.models.MunroListEntry

@Composable
fun MunroListScreen(
    navController: NavController,
    viewModel: MunroListViewModel = hiltViewModel()
){
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "MUNRO BAGGER",
                Modifier
                    .align(CenterHorizontally)
            )
            searchBar(
                hint = "Search Munro's ...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                viewModel.searchMunroList(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            MunroList(navController = navController)
        }
    }
}

@Composable
fun searchBar (
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
){
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember{
        mutableStateOf(hint != "")
    }
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.hasFocus != true
                }

        )
        if (isHintDisplayed){
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun MunroList(
    navController: NavController,
    viewModel: MunroListViewModel = hiltViewModel()
){
    val munroList by remember {viewModel.munroList}
    val loadError by remember {viewModel.loadError}
    val isLoading by remember {viewModel.isLoading}
    val isSearching by remember {viewModel.isSearching}

    LazyColumn(contentPadding = PaddingValues(16.dp)){
        val itemCount = if(munroList.size % 2 == 0){
            munroList.size /2
        }else{
            munroList.size / 2 + 1
        }
        items(itemCount){
            MunroRow(rowIndex = it , entries = munroList, navController = navController)
        }
    }
    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ){
        if(isLoading){
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if(loadError.isNotEmpty()){
            retrySection(error = loadError) {
                viewModel.loadMunro()
            }
        }
    }
}



@Composable
fun MunroEntry(
     entry: MunroListEntry,
     navController: NavController,
     modifier: Modifier = Modifier,
     viewModel: MunroListViewModel = hiltViewModel()
){
    Box(
        contentAlignment = Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colors.primary,
                        MaterialTheme.colors.surface
                    )
                )
            )
            .clickable {
                navController.navigate("munroDetailScreen/${entry.munroName}")
            }
    ){
        Column {
            Text(text = entry.munroName,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .fillMaxWidth()
            )
        }
    }
}
@Composable
fun MunroRow(
    rowIndex: Int,
    entries: List<MunroListEntry>,
    navController: NavController
){
    Column {
        Row {
            MunroEntry(
                entry = entries[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (entries.size >= rowIndex * 2 +2){
                MunroEntry(
                    entry = entries[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else{
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun retrySection(
    error: String,
    onRetry: () -> Unit
){
    Column {
        Text(text = error, fontSize = 18.sp, color = Color.Red)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry()},
            modifier = Modifier.align(CenterHorizontally)
        ){
            Text(text = "Retry")
        }
    }
}