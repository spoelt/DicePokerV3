package com.spoelt.dicepoker.data.repository

import com.spoelt.dicepoker.core.domain.DataState
import com.spoelt.dicepoker.data.local.GameDao
import com.spoelt.dicepoker.domain.model.Game
import com.spoelt.dicepoker.domain.model.toGameEntity
import com.spoelt.dicepoker.domain.model.toGameList
import com.spoelt.dicepoker.domain.model.toPlayersEntityList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * This is an implementation of a [GameRepository] that specifies how games should be fetched and
 * saved.
 */
class GameRepositoryImpl @Inject constructor(
    private val dao: GameDao
) : GameRepository {

    override fun fetchSavedGames(): Flow<DataState<List<Game>>> = dao
        .getGamesWithPlayers()
        .map { entityList ->
            DataState.Success(entityList.toGameList())
        }
        .catch { exception ->
            DataState.Error(exception)
        }

    override suspend fun saveGame(game: Game): Boolean {
        dao.apply {
            val insertedGameRow = insertGame(game.toGameEntity())
            val insertedPlayers = insertPlayers(game.players.toPlayersEntityList())
            return insertedGameRow > 0 && insertedPlayers.size == game.players.size
        }
    }
}
