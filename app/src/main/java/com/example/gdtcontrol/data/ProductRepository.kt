package com.example.gdtcontrol.data

import com.example.gdtcontrol.Product
import com.example.gdtcontrol.ProductService


class ProductRepository(
    private val productDao: ProductDao,
    private val productService: ProductService
    ) {


    fun getProductById(id: Int): Product? {
        val product = productDao.selectProduct(id)
        return product?.let {
            Product(
                it.name,
                it.description,
                it.stock,
                it.stockMax,
                it.stockMin,
                it.proveedor,
                it.emailProveedor
            )
        }
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
        productService.createProduct(entity)
        try {
            productDao.addProduct(entity)
        }catch (e: Exception){
            //
        }



    }

    suspend fun getLastProductId(): Int? {
        return productDao.getLastProduct()?.id
    }

    suspend fun updateProduct(product: Product, code: String) {
        val entity = ProductEntity(
            id = code.toInt(),
            name = product.name,
            description = product.description,
            stock = product.stock,
            stockMax = product.stockMax,
            stockMin = product.stockMin,
            proveedor = product.proveedor,
            emailProveedor = product.emailProveedor
        )
        productDao.updateProduct(entity)


    }

    suspend fun deleteProduct(product: Product, code: String) {
        val entity = ProductEntity(
            id = code.toInt(),
            name = product.name,
            description = product.description,
            stock = product.stock,
            stockMax = product.stockMax,
            stockMin = product.stockMin,
            proveedor = product.proveedor,
            emailProveedor = product.emailProveedor
        )

        productDao.deleteProduct(entity)

    }



}