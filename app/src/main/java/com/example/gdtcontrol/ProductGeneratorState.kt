package com.example.gdtcontrol

data class ProductGeneratorState(
    val name:String = "",
    val description:String= "",
    val stock:Int=0,
    val stockMax: Int=0,
    val stockMin:Int=0,
    val proveedor:String="",
    val emailProveedor:String= "",
)
