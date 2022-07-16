package com.spoelt.dicepoker.data.repository

import com.spoelt.dicepoker.domain.model.CreateGameResult
import com.spoelt.dicepoker.domain.model.GameOptions
import com.spoelt.dicepoker.domain.model.Player

/**
 * This is the data contract for any requests to fetch or save games.
 */
interface GameRepository {
/*

    */
/**
     * Request all of the games that have been saved locally on the device.
     *//*

    fun fetchSavedGames(): Flow<DataState<List<Game>>>

    */
/**
     * Save the data of a [game]. This will only be triggered for games that have been played until
     * finished.
     *//*

    suspend fun saveGame(game: Game): Boolean
*/

    /**
     * After the user has selected the options of the game as well as entered the players' names,
     * this initial data will be stored in the data base.
     *
     * @param[gameOptions] The options of the game the user has selected.
     * @param[players] The list of players involved in the game.
     */
    suspend fun createGame(gameOptions: GameOptions, players: List<Player>): CreateGameResult
}
