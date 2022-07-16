package com.spoelt.dicepoker.domain.usecase

import com.spoelt.dicepoker.data.repository.GameRepository
import com.spoelt.dicepoker.domain.model.CreateGameResult
import com.spoelt.dicepoker.domain.model.GameOptions
import com.spoelt.dicepoker.domain.model.Player
import javax.inject.Inject

class CreateGameUseCaseImpl @Inject constructor(
    private val gameRepository: GameRepository
) : CreateGameUseCase {

    override suspend fun invoke(
        gameOptions: GameOptions,
        players: List<Player>
    ): CreateGameResult = gameRepository.createGame(gameOptions, players)
}
