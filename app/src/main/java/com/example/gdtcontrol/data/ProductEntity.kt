package com.example.gdtcontrol.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name:String,
    val description:String,
    val stock:Int,
    val stockMax: Int,
    val stockMin:Int,
    val proveedor:String,
    val emailProveedor:String,
)