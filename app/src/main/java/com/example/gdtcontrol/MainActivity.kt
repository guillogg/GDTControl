package com.example.gdtcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.gdtcontrol.data.ProductDatabase
import com.example.gdtcontrol.data.ProductRepository
import com.example.gdtcontrol.navigation.navigation
import com.example.gdtcontrol.ui.theme.GDTControlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(this, ProductDatabase::class.java, "product_database")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val dao = db.productDao
        val instancia = ProductService.instance
        val repository = ProductRepository(dao,instancia)
        val viewmodel = ViewModel(repository,instancia)
        enableEdgeToEdge()
        setContent {
            GDTControlTheme {
                val navController = rememberNavController()
                navigation(navController, viewmodel)
            }
        }
    }
}

