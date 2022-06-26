package com.spoelt.dicepoker.domain.usecase

import com.spoelt.dicepoker.domain.model.CreateGameResult
import com.spoelt.dicepoker.domain.model.GameOptions

/**
 * This use case consumes any information required to create a new game of dice poker and attempts
 * to do so.
 */
interface CreateGameUseCase {
    suspend operator fun invoke(
        gameOptions: GameOptions
    ): CreateGameResult
}
