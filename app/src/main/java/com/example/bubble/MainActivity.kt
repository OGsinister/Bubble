package com.example.bubble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bubble.core.ui.theme.BubbleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BubbleTheme{

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview(){
    BubbleTheme {

    }
}
