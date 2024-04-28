package com.example.bubble.relax.use_cases

import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.relax.model.RelaxState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBubblesUseCase @Inject constructor(
    private val bubbleDispatchers: BubbleDispatchers,
) {
    operator fun invoke(): Flow<RelaxState> {
        return flow {

            emit(RelaxState.IsLoadingState)
            try {
                /*val fakeData = bubbleStorage.getBubbleCounts()
                emit(RelaxState.LoadedDataState(fakeData))*/
            }catch (e: Exception){
                emit(RelaxState.ErrorDataState(errorMessage = e.localizedMessage?.toString()))
            }finally {
                emit(RelaxState.DefaultState())
            }
        }
    }
}