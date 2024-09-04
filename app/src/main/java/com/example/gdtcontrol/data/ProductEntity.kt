package com.example.gdtcontrol.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product_table")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") // Si el campo en el JSON es "id"
    val id: Int? = null,

    @SerializedName("name") // Si el campo en el JSON es "name"
    val name: String,

    @SerializedName("description") // Si el campo en el JSON es "description"
    val description: String,

    @SerializedName("stock") // Si el campo en el JSON es "stock"
    val stock: Int,

    @SerializedName("stock_max") // Si el campo en el JSON es "stock_max"
    val stockMax: Int,

    @SerializedName("stock_min") // Si el campo en el JSON es "stock_min"
    val stockMin: Int,

    @SerializedName("proveedor") // Si el campo en el JSON es "proveedor"
    val proveedor: String,

    @SerializedName("email_proveedor") // Si el campo en el JSON es "email_proveedor"
    val emailProveedor: String
)
