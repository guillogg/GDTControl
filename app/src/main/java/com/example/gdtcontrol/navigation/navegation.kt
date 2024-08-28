package com.example.gdtcontrol.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gdtcontrol.MenuScreen
import com.example.gdtcontrol.ProductDetailScreen
import com.example.gdtcontrol.ProductGeneratorScreen
import com.example.gdtcontrol.ScannerScreen
import com.example.gdtcontrol.ViewModel

@Composable
fun navigation (navController: NavHostController, viewModel: ViewModel){
    NavHost(navController = navController, startDestination = Appscreens.MenuScreen.route){
        composable(route = Appscreens.MenuScreen.route){
            MenuScreen(navController)
        }
        composable(route = Appscreens.ScannerScreen.route){
            ScannerScreen(viewModel,navController)
        }
        composable(route = Appscreens.ProductGeneratorScreen.route){
            ProductGeneratorScreen(viewModel)
        }
        composable(route = Appscreens.ProductDetailScreen.route + "/{code}",
            arguments = listOf(navArgument(name = "code"){type = NavType.StringType})
        ){backStackEntry ->
            val code = backStackEntry.arguments?.getString("code")
            if (code != null){

                ProductDetailScreen(viewModel,navController, code = code)
            }

        }

    }
}
