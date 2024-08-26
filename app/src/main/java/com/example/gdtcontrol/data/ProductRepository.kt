package com.example.gdtcontrol.data

import com.example.gdtcontrol.Product

class ProductRepository(private val productDao: ProductDao) {

    //ver mas adelante agregar la funcion para obtener todos los productos
    suspend fun getProduct(id: Int): Product {
        val entity = productDao.selectProduct(id)
        return Product(
            entity.name,
            entity.description,
            entity.stock,
            entity.stockMax,
            entity.stockMin,
            entity.proveedor,
            entity.emailProveedor
        )

    }

    suspend fun addProduct(product: Product)  {
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