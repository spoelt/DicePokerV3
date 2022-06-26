package com.spoelt.dicepoker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spoelt.dicepoker.data.model.GameEntity
import com.spoelt.dicepoker.data.model.PlayerEntity

@Database(
    entities = [
        GameEntity::class,
        PlayerEntity::class
    ],
    version = 1
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun dao(): GameDao
}
