package com.example.gdtcontrol

import com.example.gdtcontrol.data.ProductEntity
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductService {
    companion object {
        val instance: ProductService by lazy {
            Retrofit.Builder()
                .baseUrl("https://crudcrud.com/api/09f19869120143219f5fdcc74f451422/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()
                .create(ProductService::class.java)
        }
    }


    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): Response<ProductEntity>

    @POST("products")
    suspend fun createProduct(@Body product: ProductEntity): Response<ProductEntity>

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id: String, @Body product: ProductEntity): Response<ProductEntity>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: String): Response<Unit>


}