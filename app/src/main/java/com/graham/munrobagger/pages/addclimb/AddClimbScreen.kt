package com.graham.munrobagger.pages.addclimb

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.graham.munrobagger.data.local.tables.Climbes
import com.graham.munrobagger.pages.composables.PageTitle
import com.graham.munrobagger.pages.composables.TopSection
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@Composable
fun AddClimbScreen (
    munroNumber: String,
    navController: NavController,
    viewModel: AddClimbViewModel = hiltViewModel(),
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)
        .padding(16.dp)
    ){
        Column() {
            TopSection(navController)
            PageTitle("Add New Climb")
            AddClimbScreenContent(){
                viewModel.addClimb(it)
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun AddClimbScreenContent(
    onBtnClick: (Climbes) -> Unit
){
    var munroNumber by remember { mutableStateOf("")}
    var munroName by remember { mutableStateOf("")}
    var date by remember { mutableStateOf("")}
    var weather by remember { mutableStateOf("")}
    var distance by remember { mutableStateOf("")}
    var duration  by remember { mutableStateOf("")}
    var friends  by remember { mutableStateOf("")}

    Box(
        Modifier
            .fillMaxHeight()
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.onPrimary)
            .padding(16.dp)
    ) {
        Column() {
            Text(text = "Climb Details",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier
                .height(35.dp))

            NumberInputRow("Munro Number"){ textFieldValue ->
                munroNumber = textFieldValue
            }
            inputRow("Munro Name", 100){ textFieldValue ->
                munroName = textFieldValue
            }
            inputRow("Date", 100){ textFieldValue ->
                date = textFieldValue
            }
            inputRow("Weather", 100){ textFieldValue ->
                weather = textFieldValue
            }
            inputRow("Distance", 100){ textFieldValue ->
                distance = textFieldValue
            }
            inputRow("Duration", 100){ textFieldValue ->
                duration = textFieldValue
            }
            inputRow("Friends", 100){ textFieldValue ->
                friends = textFieldValue
            }
            ImageRow()

            Spacer(modifier = Modifier.weight(1F))

            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    onBtnClick(Climbes(munroNumber.toInt(), date.toInt(), weather, distance.toInt(), duration.toFloat(), friends))
            }) {
                Text(text = "ADD CLIMB")
            }
        }
    }
}


//@Composable
//fun ButtonWithIcon() {
//    Button(onClick = {}) {
//        Image(
//            painterResource(id = R.drawable.ic_cart),
//            contentDescription ="Cart button icon",
//            modifier = Modifier.size(20.dp))
//
//        Text(text = "Add to cart",Modifier.padding(start = 10.dp))
//    }
//}

@Composable
fun inputRow(
    rowName: String,
    width: Int,
    onValueChange: (String) -> Unit
){
    var value by remember {
        mutableStateOf("")
    }

    Row {
        Text(
            text = rowName + ": ",
            fontSize = 18.sp,
            modifier = Modifier
                .weight(1F)
        )
        BasicTextField(
            modifier = Modifier
                .weight(1F),
            value = value,
            onValueChange = { newText ->
                onValueChange(newText)
                value = newText},

//            onValueChange = {
//                if (it.isEmpty()) {
//                    text = it.filter { symbol ->
//                        symbol.isDigit()
//                    }
//                }
//            }



                    decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .height(30.dp)
                        .background(Color.LightGray, RoundedCornerShape(percent = 30))
                        .padding(3.dp)
                ) {
                    Spacer(Modifier.width(16.dp))
                    innerTextField()
                }
            }
        )
    }
    Spacer(modifier = Modifier
        .height(12.dp))
}

@Composable
fun NumberInputRow(
    rowName: String,
    onValueChange: (String) -> Unit
){


    Row {
        Text(
            text = rowName + ": ",
            fontSize = 18.sp,
            modifier = Modifier
                .weight(1F)
        )

        var value by remember {
            mutableStateOf("")
        }
        BasicTextField(
            modifier = Modifier
                .weight(1F),
            value = value,
            onValueChange = { newText ->
                onValueChange(newText)
                value = newText.filter { symbol ->
                    symbol.isDigit() }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .height(30.dp)
                        .background(Color.LightGray, RoundedCornerShape(percent = 30))
                        .padding(3.dp)
                ) {
                    Spacer(Modifier.width(16.dp))
                    innerTextField()
                }
            }
        )
    }
    Spacer(modifier = Modifier
        .height(12.dp))
}


// place holder for add image function
@Composable
fun ImageRow(){

    var value by remember {
        mutableStateOf("")
    }
    Row {
        Text(
            text = "Photo : ",
            fontSize = 18.sp,
            modifier = Modifier
//               .align(CenterVertically)
                .weight(1F)
        )
        BasicTextField(
            modifier = Modifier
                .weight(1F),
            value = value,
            onValueChange = { newText ->
                value = newText
            },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .height(100.dp)
                        .background(Color.LightGray, RoundedCornerShape(percent = 10))
                        .padding(3.dp)
                ) {
                    Spacer(Modifier.width(16.dp))
                    innerTextField()
                }
            }
        )
    }
    Spacer(modifier = Modifier
        .height(12.dp))
}





@Preview
@Composable
fun Test(){
    AddClimbScreenContent(){}
}


//@Composable
//fun inputRow(
//    rowName: String,
//    width: Int,
//    onValueChange: (String) -> Unit
//){
//    var value by remember {
//        mutableStateOf("")
//    }
//
//    Row {
//        Text(
//            text = rowName + ": ",
//            fontSize = 18.sp,
//            modifier = Modifier
//                .weight(1F)
//        )
//        BasicTextField(
//            modifier = Modifier
//                .weight(1F),
//            value = value,
//            onValueChange = { newText ->
//                onValueChange(newText)
//                value = newText},
//
////            onValueChange = {
////                if (it.isEmpty()) {
////                    text = it.filter { symbol ->
////                        symbol.isDigit()
////                    }
////                }
////            }
//
//
//
//            decorationBox = { innerTextField ->
//                Row(
//                    modifier = Modifier
//                        .height(30.dp)
//                        .background(Color.LightGray, RoundedCornerShape(percent = 30))
//                        .padding(3.dp)
//                ) {
//                    Spacer(Modifier.width(16.dp))
//                    innerTextField()
//                }
//            }
//        )
//    }
//    Spacer(modifier = Modifier
//        .height(12.dp))
//}