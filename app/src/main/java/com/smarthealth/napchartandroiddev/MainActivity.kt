package com.smarthealth.napchartandroiddev

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.smarthealth.napchartandroiddev.ui.theme.NapchartAndroidDevTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NapchartAndroidDevTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    // todo pass here schedule component to test its interactive behaviour
                }
            }
        }
    }
}