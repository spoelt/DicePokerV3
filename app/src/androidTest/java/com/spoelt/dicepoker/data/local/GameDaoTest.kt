package com.spoelt.dicepoker.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class GameDaoTest {
    private lateinit var dao: GameDao
    private lateinit var db: GameDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            GameDatabase::class.java
        ).build()
        dao = db.dao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertGame() = runTest {
        val result = dao.insertGame(testGame)
        assertThat(result).isGreaterThan(0)
    }

    @Test
    fun insertPlayers() = runTest {
        val result = dao.insertPlayers(testPlayers)
        assertThat(result.last()).isEqualTo(testPlayers.size.toLong())
    }

    @Test
    fun getGamesWithPlayers() = runTest {
        val playerIds = testPlayers.map { it.playerId }

        dao.apply {
            insertGame(testGame)
            insertPlayers(testPlayers)
        }

        val result = dao.getGamesWithPlayers().first()
        assertThat(result.size).isEqualTo(1)

        val resultPlayerIds = result.flatMap { r -> r.players.map { player -> player.playerId } }
        assertThat(resultPlayerIds).isEqualTo(playerIds)
    }

    @Test
    fun deleteAllGames() = runTest {
        testGames.forEach { game ->
            dao.insertGame(game)
        }
        dao.deleteAllGames()
        val result = dao.getGamesWithPlayers().first()
        assertThat(result).isEmpty()
    }

    @Test
    fun deleteAllPlayers() = runTest {
        dao.apply {
            insertGame(testGame)
            insertPlayers(testPlayers)
            deleteAllPlayers()
        }
        val result = dao.getGamesWithPlayers().first()
        assertThat(result.flatMap { it.players }).isEmpty()
    }
}
