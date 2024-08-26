package com.example.gdtcontrol

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdtcontrol.data.ProductRepository
import kotlinx.coroutines.launch

class ViewModel(private val productRepository: ProductRepository) : ViewModel() {
    var state by mutableStateOf(ProductGeneratorState())
        private set

    fun onNameChange(name: String) {
        state = state.copy(name = name)
    }

    fun onDescriptionChange(description: String) {
        state = state.copy(description = description)
    }

    fun onStockChange(stock: Int) {
        state = state.copy(stock = stock)
    }

    fun onStockMinChange(stockMin: Int) {
        state = state.copy(stockMin = stockMin)
    }

    fun onStockMaxChange(stockMax: Int) {
        state = state.copy(stockMax = stockMax)
    }

    fun onProveedorChange(proveedor: String) {
        state = state.copy(proveedor = proveedor)
    }

    fun onEmailProveedorChange(emailProveedor: String) {
        state = state.copy(emailProveedor = emailProveedor)
    }

    fun addProduct() {

        val product = Product(
            state.name,
            state.description,
            state.stock,
            state.stockMin,
            state.stockMax,
            state.proveedor,
            state.emailProveedor
        )
        viewModelScope.launch {
            productRepository.addProduct(product)

        }


    }

    suspend fun getLastProductId(): Int? {
        return productRepository.getLastProductId()
    }
    fun getProduct() {
        viewModelScope.launch {
            productRepository.getProduct(1)
        }
    }
}