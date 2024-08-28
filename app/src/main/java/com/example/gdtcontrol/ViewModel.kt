package com.example.gdtcontrol

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdtcontrol.data.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ViewModel(private val productRepository: ProductRepository) : ViewModel() {
    var state by mutableStateOf(ProductGeneratorState())
        private set

    var isFormVisible by mutableStateOf(true)

    val product = MutableLiveData<Product>()

    fun getProductById(productId: String): Product {

        val currentproduct = productRepository.getProductById(productId.toInt())

        return currentproduct

    }

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
        validateStockValues()
    }

    fun onStockMaxChange(stockMax: Int) {
        state = state.copy(stockMax = stockMax)
        validateStockValues()
    }

    fun onProveedorChange(proveedor: String) {
        state = state.copy(proveedor = proveedor)
    }

    fun onEmailProveedorChange(emailProveedor: String) {
        state = state.copy(emailProveedor = emailProveedor)
    }
    private fun validateStockValues() {
        if (state.stockMin > state.stockMax) {
            state = state.copy(stockMin = state.stockMax)
        }
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

    fun resetForm() {
        state = ProductGeneratorState()
    }

    fun hideForm() {
        isFormVisible = false
        isFormVisible = true
    }
//lo nuevo

    //hasta aca
    suspend fun getLastProductId(): Int? {
        return productRepository.getLastProductId()
    }

}