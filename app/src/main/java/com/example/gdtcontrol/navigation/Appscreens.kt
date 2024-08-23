package com.example.gdtcontrol.navigation

sealed class Appscreens(val route: String) {
    object MenuScreen: Appscreens("MenuScreen")
    object ScannerScreen: Appscreens("ScannerScreen")

    object ProductGeneratorScreen: Appscreens("ProductGeneratorScreen")
}