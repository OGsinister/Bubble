package com.example.bubble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun CheckTheme(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(BubbleTheme.shapes.basePadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Some text",
            style = BubbleTheme.typography.heading,
            color = BubbleTheme.colors.primaryBackgroundColor
        )
        Spacer(modifier = Modifier.padding(BubbleTheme.shapes.basePadding))
        Text(
            text = "Another text",
            style = BubbleTheme.typography.body,
            color = BubbleTheme.colors.primaryBackgroundColor
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview(){
    BubbleTheme {
        CheckTheme()
    }
}
