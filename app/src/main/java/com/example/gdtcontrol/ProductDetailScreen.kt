package com.example.gdtcontrol

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController

@Composable
fun ProductDetailScreen(
    viewModel: ViewModel,
    navController: NavHostController,
    code: String
) {
    val product = viewModel.getProductById(code)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Añade padding para que no esté pegado a los bordes
        contentAlignment = Alignment.Center // Coloca el contenido en la parte superior
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), // Hace que el contenido ocupe todo el ancho
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre los elementos
        ) {
            Text(text = "Product Details") // Título sin estilo de tipografía
            Divider() // Línea divisoria entre el título y los detalles
            Text(text = "Name: ${product.name}")
            Text(text = "Description: ${product.description}")
            Text(text = "Stock: ${product.stock}")
            Text(text = "Stock Max: ${product.stockMax}")
            Text(text = "Stock Min: ${product.stockMin}")
            Text(text = "Proveedor: ${product.proveedor}")
            Text(text = "Email Proveedor: ${product.emailProveedor}")
        }
    }
}

