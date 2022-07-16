package com.spoelt.dicepoker.data.repository

import com.spoelt.dicepoker.data.local.GameDao
import com.spoelt.dicepoker.domain.model.CreateGameResult
import com.spoelt.dicepoker.domain.model.GameNavArg
import com.spoelt.dicepoker.domain.model.GameOptions
import com.spoelt.dicepoker.domain.model.Player
import com.spoelt.dicepoker.domain.model.toGameEntity
import com.spoelt.dicepoker.domain.model.toPlayersEntityList
import java.util.UUID
import javax.inject.Inject

/**
 * This is an implementation of a [GameRepository] that specifies how games should be fetched and
 * saved.
 */
class GameRepositoryImpl @Inject constructor(
    private val dao: GameDao
) : GameRepository {

    /*override fun fetchSavedGames(): Flow<DataState<List<Game>>> = dao
        .getGamesWithPlayers()
        .map { entityList ->
            DataState.Success(entityList.toGameList())
        }
        .catch { exception ->
            DataState.Error(exception)
        }

    override suspend fun saveGame(game: Game): Boolean {
        dao.apply {
            val insertedGameRow = insertGame(game.toGameEntity(game.))
            val insertedPlayers = insertPlayers(game.players.toPlayersEntityList())
            return insertedGameRow > 0 && insertedPlayers.size == game.players.size
        }
    }*/

    override suspend fun createGame(
        gameOptions: GameOptions,
        players: List<Player>
    ): CreateGameResult {
        dao.apply {
            val gameId = UUID.randomUUID().toString()
            val insertedGameRow = insertGame(
                game = gameOptions.toGameEntity(gameId = gameId)
            )
            val insertedPlayers = insertPlayers(
                players = players.toPlayersEntityList(gameId = gameId)
            )
            return when {
                insertedGameRow > 0 && insertedPlayers.size == players.size -> CreateGameResult.Success(
                    gameNavArg = GameNavArg(id = gameId)
                )
                else -> CreateGameResult.Failure
            }
        }
    }
}
