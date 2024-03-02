package com.example.bubble

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.award.useCase.AddAwardUseCase
import com.example.bubble.award.useCase.UpdateAwardUseCase
import com.example.bubble.award.utils.AwardCodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateAwardUseCase: UpdateAwardUseCase,
    //private val addAwardUseCase: AddAwardUseCase
): ViewModel() {

    fun updateAchiv(code: AwardCodes){
        viewModelScope.launch(Dispatchers.IO) {
            updateAwardUseCase.updateAward(code)
        }
    }

    /*fun addAward(){
        val newAwardEntity = AwardEntity(id = 1, name = "ttqwtw", isOpen = false)
        viewModelScope.launch(Dispatchers.IO) {
            addAwardUseCase.invoke(newAwardEntity)
        }
    }*/
}
