package com.example.gdtcontrol

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gdtcontrol.navigation.Appscreens


@Composable
fun MenuScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate(Appscreens.ScannerScreen.route) },
                modifier = Modifier.size(200.dp, 50.dp)
            ) {
                Text(text = "Scanear")
            }
            Button(
                onClick = { navController.navigate(Appscreens.ProductGeneratorScreen.route) },
                modifier = Modifier.size(200.dp, 50.dp)
            ) {
                Text(text = "Agregar Producto")
            }
        }
    }
}