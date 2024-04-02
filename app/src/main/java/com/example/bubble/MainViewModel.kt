package com.example.bubble

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.award.useCase.AddAwardUseCase
import com.example.bubble.award.useCase.UpdateAwardUseCase
import com.example.bubble.award.utils.AwardCodes
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.data.local.database.dbo.AwardEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateAwardUseCase: UpdateAwardUseCase,
    private val bubbleDispatchers: BubbleDispatchers,
    private val addAwardUseCase: AddAwardUseCase
): ViewModel() {

    fun updateAchiv(code: AwardCodes){
        viewModelScope.launch(bubbleDispatchers.io) {
            updateAwardUseCase.updateAward(code)
        }
    }

    fun addAchiv(){
        viewModelScope.launch(bubbleDispatchers.io) {
            val awardEntity = AwardEntity(
                name = "name",
                title = "title",
                isUnlocked = false
            )
            addAwardUseCase.invoke(awardEntity)
        }
    }


}
