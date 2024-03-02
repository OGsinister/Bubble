package com.example.bubble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bubble.award.presentation.AwardScreen
import com.example.bubble.core.ui.theme.BubbleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BubbleTheme{
                AwardScreen()
                //CheckAwardSet()
            }
        }
    }
}

/*@Composable
fun CheckAwardSet(
    viewModel: MainViewModel = hiltViewModel()
){
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            //viewModel.addAward()
            viewModel.updateAchiv(AwardCodes.FIRST_BUBBLE_CLICK)
        }) {
            Text(text = "Click")
        }
    }
}*/

@Composable
@Preview(showBackground = true)
fun DefaultPreview(){
    BubbleTheme {

    }
}
