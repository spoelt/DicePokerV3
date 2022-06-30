package com.spoelt.dicepoker.data.repository

import com.spoelt.dicepoker.core.domain.DataState
import com.spoelt.dicepoker.domain.model.Game
import kotlinx.coroutines.flow.Flow

/**
 * This is the data contract for any requests to fetch or save games.
 */
interface GameRepository {

    /**
     * Request all of the games that have been saved locally on the device.
     */
    fun fetchSavedGames(): Flow<DataState<List<Game>>>

    /**
     * Save the data of a [game]. This will only be triggered for games that have been played until
     * finished.
     */
    suspend fun saveGame(game: Game): Boolean
}
