package com.example.gdtcontrol.data

import com.example.gdtcontrol.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepository(private val productDao: ProductDao) {

    //ver mas adelante agregar la funcion para obtener todos los productos
    fun getProductById(id: Int): Product {
        val product = productDao.selectProduct(id)
        return Product(
            product!!.name,
            product.description,
            product.stock,
            product.stockMax,
            product.stockMin,
            product.proveedor,
            product.emailProveedor
        )

    }


    suspend fun addProduct(product: Product) {
        val entity = ProductEntity(
            name = product.name,
            description = product.description,
            stock = product.stock,
            stockMax = product.stockMax,
            stockMin = product.stockMin,
            proveedor = product.proveedor,
            emailProveedor = product.emailProveedor
        )
        productDao.addProduct(entity)

    }

    suspend fun getLastProductId(): Int? {
        return productDao.getLastProduct()?.id
    }


}