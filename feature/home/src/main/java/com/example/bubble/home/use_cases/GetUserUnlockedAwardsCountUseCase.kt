package com.example.bubble.home.use_cases

import com.example.bubble.data.repository.AwardRepository
import javax.inject.Inject

class GetUserUnlockedAwardsCountUseCase @Inject constructor(
    private val repository: AwardRepository
) {
  suspend operator fun invoke(): Int {
      return repository.getUserUnlockedAwards().count()
  }
}