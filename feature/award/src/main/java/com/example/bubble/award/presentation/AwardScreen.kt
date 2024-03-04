package com.example.bubble.award.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.award.AwardViewModel
import com.example.bubble.award.model.AwardState
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.domain.model.Award

@Composable
fun AwardScreen(
    modifier: Modifier = Modifier,
    viewModel: AwardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val currentState = state


    if(currentState != AwardState.DefaultState){
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            if(currentState is AwardState.IsLoadingState){
                LoadingStateScreen()
            }

            if(currentState is AwardState.EmptyDataState){
                EmptyDataScreen(currentState.message)
            }

            if(currentState is AwardState.ErrorState){
                ErrorStateScreen(currentState.message)
            }

            if(currentState is AwardState.LoadedAwardsState){
                LoadedAwardStateScreen(modifier, currentState.data)
            }
        }
    }
}

@Composable
private fun LoadingStateScreen(){
    CircularProgressIndicator()
}

@Composable
private fun EmptyDataScreen(message: String){
    Text(text = message)
}

@Composable
private fun ErrorStateScreen(error: String){
    Text(text = error)
}

@Composable
private fun LoadedAwardStateScreen(modifier: Modifier, awards: List<Award>){
    LazyColumn {
        items(
            items = awards,
            key = {
                it.id ?: 0
            }
        ){
            AwardItem(modifier = modifier, item = it)
        }
    }
}

@Composable
private fun AwardItem(
    modifier: Modifier,
    item: Award
){
    val cardColor = if(item.isUnlocked == true){
        BubbleTheme.colors.notificationColor
    }else{
        BubbleTheme.colors.secondaryBackgroundColor
    }

    Card(
        modifier = modifier
            .padding(BubbleTheme.shapes.itemsPadding)
            .clip(BubbleTheme.shapes.cornerStyle),
        colors = CardDefaults.cardColors(cardColor)
    ) {
        Row(
            modifier = modifier
                .padding(BubbleTheme.shapes.basePadding)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null
            )
            Column(
                modifier = modifier
                    .padding(BubbleTheme.shapes.basePadding),
                verticalArrangement = Arrangement.spacedBy(BubbleTheme.shapes.itemsPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = item.name.toString(),
                    style = BubbleTheme.typography.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    color = BubbleTheme.colors.primaryTextColor
                )

                Text(
                    text = item.title.toString(),
                    style = BubbleTheme.typography.body,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    color = BubbleTheme.colors.primaryTextColor
                )
            }
        }
    }
}