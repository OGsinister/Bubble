package com.example.bubble.water.useCases

import android.util.Log
import com.example.bubble.data.local.sharedPref.WaterSharedPref
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.domain.model.Water
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWaterUseCase @Inject constructor(
    private val waterSharedPref: WaterSharedPref
) {
    operator fun invoke(): Flow<DatabaseResource<Water>> {
        return flow {
            emit(DatabaseResource.Loading())
            try {
                val waterCount = waterSharedPref.getBubbleCount()

                if(waterCount == 0){
                    emit(DatabaseResource.Empty(message = "Empty"))
                }

                emit(DatabaseResource.LoadedData(
                    loadedData = Water(
                        currentWater = waterCount,
                        title = "Столько шаров вы лопнули",
                        comparison = makeComparison(waterCount)
                    )
                ))
            }catch (e: Exception){
                emit(DatabaseResource.Error(message = e.localizedMessage))
                Log.d("Water", e.localizedMessage.toString())
            }
        }
    }
}

fun makeComparison(bubbleCount: Int): String?{
    val totalBubbleVolume = bubbleCount * 0.5f

    when(totalBubbleVolume){
        0f -> { return "Вы не лопали шары" }
        0.5f -> { return "Это как стакан воды 🥛" }
        in 1f..5f -> { return "Это чуть больше литрового сока 👍" }
        in 10f..20f -> { return "Это примерно как половина набранной ванны" }
    }
    return null
}