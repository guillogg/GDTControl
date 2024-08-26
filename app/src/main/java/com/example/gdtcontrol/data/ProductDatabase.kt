package com.example.gdtcontrol.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 2, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract val productDao: ProductDao



}