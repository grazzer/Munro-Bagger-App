package com.graham.munrobagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.graham.munrobagger.munrolist.MunroListScreen
import com.graham.munrobagger.ui.theme.MunroBaggerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MunroBaggerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "munroListScreen"
                ){
                    composable("munroListScreen"){
                        MunroListScreen(navController = navController)
                    }
                    composable("munroDetailScreen/{munroName}",
                        arguments = listOf(
                            navArgument("munroName"){
                                type = NavType.StringType
                            }
                        )
                    ){
                        val dominantName = remember {
                            it.arguments?.getString("munroName")
                        }
                    }
                }
            }
        }
    }
}

