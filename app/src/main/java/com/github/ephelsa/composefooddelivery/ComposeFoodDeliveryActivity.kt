package com.github.ephelsa.composefooddelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import coil.annotation.ExperimentalCoilApi
import com.github.ephelsa.composefooddelivery.ui.ComposeFoodDeliveryApp
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@AndroidEntryPoint
@ExperimentalAnimationApi
class ComposeFoodDeliveryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFoodDeliveryApp()
        }
    }
}
