package com.example.bubble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.award.utils.AwardCodes
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.water.presentation.WaterScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BubbleTheme{
                //AwardScreen()
                //CheckAwardSet()
                WaterScreen()
            }
        }
    }
}

@Composable
fun CheckAwardSet(
    viewModel: MainViewModel = hiltViewModel()
){
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            //viewModel.addAward()
            viewModel.updateAchiv(AwardCodes.FIRST_BUBBLE_DONE)
        }) {
            Text(text = "Click")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview(){
    BubbleTheme {

    }
}
