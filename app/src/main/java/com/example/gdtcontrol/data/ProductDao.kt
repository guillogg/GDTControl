package com.example.gdtcontrol.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(productEntity: ProductEntity)
    @Update
    suspend fun updateProduct(productEntity: ProductEntity)
    @Delete
    suspend fun deleteProduct(productEntity: ProductEntity)
    @Query("SELECT * FROM product_table WHERE id = :id")
    fun selectProduct(id: Int): ProductEntity?

    @Query("SELECT * FROM product_table ORDER BY id DESC LIMIT 1")
    suspend fun getLastProduct(): ProductEntity?
}