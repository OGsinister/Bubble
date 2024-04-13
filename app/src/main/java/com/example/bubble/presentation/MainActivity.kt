package com.example.bubble.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.bubble.MainViewModel
import com.example.bubble.award.utils.AwardCodes
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.ChangeUserNameDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BubbleTheme{
                val mainViewModel = hiltViewModel<MainViewModel>()
                val navController = rememberNavController()

                BubbleModalNavDrawer(mainViewModel = mainViewModel, navController = navController)
            }
        }
    }
}
@Composable
fun CheckAwardSet(
    viewModel: MainViewModel = hiltViewModel()
){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 150.dp)) {
        Button(onClick = {
            //viewModel.addAchiv()
            viewModel.updateAchiv(AwardCodes.FIRST_BUBBLE_CLICK)
        }) {
            Text(text = "Click")
        }
    }
}
