package com.example.gdtcontrol.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gdtcontrol.MenuScreen
import com.example.gdtcontrol.ProductGeneratorScreen
import com.example.gdtcontrol.ScannerScreen

@Composable
fun navigation (navController: NavHostController){
    NavHost(navController = navController, startDestination = Appscreens.MenuScreen.route){
        composable(route = Appscreens.MenuScreen.route){
            MenuScreen(navController)
        }
        composable(route = Appscreens.ScannerScreen.route){
            ScannerScreen(navController)
        }
        composable(route = Appscreens.ProductGeneratorScreen.route){
            ProductGeneratorScreen(navController)
        }

    }
}
