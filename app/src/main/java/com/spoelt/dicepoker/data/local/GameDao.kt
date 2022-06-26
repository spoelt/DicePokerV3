package com.spoelt.dicepoker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.spoelt.dicepoker.data.model.GameEntity
import com.spoelt.dicepoker.data.model.GameWithPlayers
import com.spoelt.dicepoker.data.model.PlayerEntity

@Dao
interface GameDao {
    @Transaction
    @Query("SELECT * FROM Game")
    suspend fun getGamesWithPlayers(): List<GameWithPlayers>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGame(game: GameEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlayers(players: List<PlayerEntity>): List<Long>

    @Query("DELETE FROM Game")
    suspend fun deleteAllGames()

    @Query("DELETE FROM Player")
    suspend fun deleteAllPlayers()
}
