package org.example.map

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

// Implementação do lado iOS
fun MainViewController(
    mapUIViewController: () -> UIViewController
) = ComposeUIViewController {
    mapViewController = mapUIViewController
    App()
}

lateinit var mapViewController: () -> UIViewController