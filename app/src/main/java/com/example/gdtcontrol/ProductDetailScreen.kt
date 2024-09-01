package com.example.gdtcontrol

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gdtcontrol.navigation.Appscreens


@Composable
fun ProductDetailScreen(
    viewModel: ViewModel,
    navController: NavHostController,
    code: String
) {

    LaunchedEffect(code) {
        viewModel.getProductById(code)
    }

    val context = LocalContext.current
    val product by viewModel.product.collectAsState()

    LaunchedEffect(product) {
        if (product == null) {

            Toast.makeText(context, "El producto no existe", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
    }
    val isFormVisible = viewModel.isFormVisible


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (product == null) {

            Text(text = "Cargando producto...")
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (isFormVisible) {
                    Text(text = "Detalles del Producto")
                    Divider()
                    Text(text = "Nombre: ${product!!.name}")
                    Text(text = "Descripcion: ${product!!.description}")
                    Text(text = "Stock: ${product!!.stock}")
                    Text(text = "Stock Max: ${product!!.stockMax}")
                    Text(text = "Stock Min: ${product!!.stockMin}")
                    Text(text = "Proveedor: ${product!!.proveedor}")
                    Text(text = "Email Proveedor: ${product!!.emailProveedor}")
                    Button(onClick = { viewModel.changeVisibility() }) {
                        Text(text = "Editar")
                    }
                } else {
                    Text(text = "Editar Producto: ${product!!.name}")
                    Divider()
                    TextField(
                        value = product!!.name,
                        onValueChange = { viewModel.updateProductName(it) },
                        label = { Text("Nombre") }
                    )
                    TextField(
                        value = product!!.description,
                        onValueChange = { viewModel.updateProductDescription(it) },
                        label = { Text("Descripcion") }
                    )
                    TextField(
                        value = product!!.stock.toString(),
                        onValueChange = { viewModel.updateProductStock(it.toIntOrNull() ?: 0) },
                        label = { Text("Stock") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    TextField(
                        value = product!!.stockMax.toString(),
                        onValueChange = { viewModel.updateProductStockMax(it.toIntOrNull() ?: 0) },
                        label = { Text("Stock Max") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    TextField(
                        value = product!!.stockMin.toString(),
                        onValueChange = { viewModel.updateProductStockMin(it.toIntOrNull() ?: 0) },
                        label = { Text("Stock Min") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    TextField(
                        value = product!!.proveedor,
                        onValueChange = { viewModel.updateProductProveedor(it) },
                        label = { Text("Proveedor") }
                    )
                    TextField(
                        value = product!!.emailProveedor,
                        onValueChange = { viewModel.updateProductEmailProveedor(it) },
                        label = { Text("Email Proveedor") }
                    )
                    Button(onClick = {
                        viewModel.updateProduct(code)
                        viewModel.changeVisibility()
                        Toast.makeText(
                            context,
                            "Se Actualizó el producto correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(Appscreens.MenuScreen.route)
                    }) {
                        Text(text = "Guardar")
                    }
                    Button(onClick = {
                        viewModel.deleteProduct(code)
                        viewModel.changeVisibility()
                        Toast.makeText(
                            context,
                            "Se Borró el producto correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(Appscreens.MenuScreen.route)
                    }) {
                        Text(text = "Eliminar")
                    }
                }
            }
        }
    }
}


