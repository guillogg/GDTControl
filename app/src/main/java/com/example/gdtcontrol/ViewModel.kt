package com.example.gdtcontrol

import MailSender
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdtcontrol.data.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModel(private val productRepository: ProductRepository,private val productService: ProductService) : ViewModel() {
    var state by mutableStateOf(ProductGeneratorState())
        private set

    var isFormVisible by mutableStateOf(true)
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product.asStateFlow()
    private val mailSender = MailSender("gdtcontrol9@gmail.com", "xpfo xpgf snmk ngll")

    fun getProductById(productId: String) {
        viewModelScope.launch {
            val currentProduct = productRepository.getProductById(productId.toInt())
            _product.value = currentProduct
        }
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


    fun onStockMaxChange(stockMax: Int) {
        state = state.copy(stockMax = stockMax)
        //validateStockValues()
    }

    fun onStockMinChange(stockMin: Int) {
        state = state.copy(stockMin = stockMin)
        // validateStockValues()
    }

    fun onProveedorChange(proveedor: String) {
        state = state.copy(proveedor = proveedor)
    }

    fun onEmailProveedorChange(emailProveedor: String) {
        state = state.copy(emailProveedor = emailProveedor)
    }



    fun updateProductName(name: String) {
        _product.value = _product.value?.copy(name = name)
    }

    fun updateProductDescription(description: String) {
        _product.value = _product.value?.copy(description = description)
    }

    fun updateProductStock(stock: Int) {
        _product.value = _product.value?.copy(stock = stock)
    }

    fun updateProductStockMax(stockMax: Int) {
        _product.value = _product.value?.copy(stockMax = stockMax)
    }

    fun updateProductStockMin(stockMin: Int) {
        _product.value = _product.value?.copy(stockMin = stockMin)
    }

    fun updateProductProveedor(proveedor: String) {
        _product.value = _product.value?.copy(proveedor = proveedor)
    }

    fun updateProductEmailProveedor(emailProveedor: String) {
        _product.value = _product.value?.copy(emailProveedor = emailProveedor)
    }

    fun addProduct() {

        val product = Product(
            state.name,
            state.description,
            state.stock,
            state.stockMax,
            state.stockMin,
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

    fun changeVisibility() {
        isFormVisible = !isFormVisible
    }

    suspend fun getLastProductId(): Int? {
        return productRepository.getLastProductId()
    }

    fun updateProduct(code: String) {
        val currentproduct = Product(
            product.value!!.name,
            product.value!!.description,
            product.value!!.stock,
            product.value!!.stockMax,
            product.value!!.stockMin,
            product.value!!.proveedor,
            product.value!!.emailProveedor
        )
        viewModelScope.launch {
            productRepository.updateProduct(currentproduct, code)
        }
    }

    fun deleteProduct(code: String) {
        val currentproduct = Product(
            product.value!!.name,
            product.value!!.description,
            product.value!!.stock,
            product.value!!.stockMin,
            product.value!!.stockMax,
            product.value!!.proveedor,
            product.value!!.emailProveedor
        )
        viewModelScope.launch {
            productRepository.deleteProduct(currentproduct, code)
        }
    }

    fun checkStockAndSendEmail(code: String) {
        getProductById(code)
        if (product.value!!.stock <= product.value!!.stockMin) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    mailSender.sendEmail(
                        "guillermo_gaona@hotmail.com",
                        "Stock Bajo del producto ${product.value?.name}",
                        "El stock del producto ${product.value?.name} es bajo, necesitamos que nos envies ${product.value!!.stockMax - product.value!!.stockMin} mas"
                    )


                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }





}

