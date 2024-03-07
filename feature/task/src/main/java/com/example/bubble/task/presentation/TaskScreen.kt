package com.example.bubble.task.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.domain.model.Task
import com.example.bubble.task.TaskViewModel
import com.example.bubble.task.model.TasksState

@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val taskState by viewModel.tasksState.collectAsState()
    val currentState = taskState

    if(currentState !is TasksState.DefaultState){
        Column(
            modifier = modifier
                .fillMaxSize()
        ){
            if(currentState is TasksState.IsLoadingState){
                LoadingStateScreen()
            }

            if(currentState is TasksState.EmptyDataState){
                EmptyDataScreen(currentState.message)
            }

            if(currentState is TasksState.ErrorState){
                ErrorStateScreen(currentState.message)
            }

            if(currentState is TasksState.LoadedDataState){
                TaskContentScreen(modifier, currentState.data)
            }
        }
    }
}

@Composable
fun TaskContentScreen(modifier: Modifier, tasks: List<Task>){

}

@Composable
fun ErrorStateScreen(message: String) {
    Text(text = message)
}

@Composable
fun EmptyDataScreen(message: String) {
    Text(text = message)
}

@Composable
fun LoadingStateScreen() {
    CircularProgressIndicator()
}

@Composable
fun AddTaskScreen(){

}

@Composable
fun TaskItemScreen(){

}

@Composable
@Preview(showBackground = true)
fun TaskScreenPreview(){
    BubbleTheme {
        TaskScreen()
    }
}