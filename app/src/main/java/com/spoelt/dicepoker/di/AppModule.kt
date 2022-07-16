package com.spoelt.dicepoker.di

import android.content.Context
import androidx.room.Room
import com.spoelt.dicepoker.data.local.GameDao
import com.spoelt.dicepoker.data.local.GameDatabase
import com.spoelt.dicepoker.data.repository.GameRepository
import com.spoelt.dicepoker.data.repository.GameRepositoryImpl
import com.spoelt.dicepoker.domain.usecase.CreateGameUseCase
import com.spoelt.dicepoker.domain.usecase.CreateGameUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGameDatabase(@ApplicationContext applicationContext: Context): GameDatabase = Room
        .databaseBuilder(
            applicationContext,
            GameDatabase::class.java,
            "game_db"
        )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideGameDao(gameDatabase: GameDatabase): GameDao = gameDatabase.dao()

    @Provides
    @Singleton
    fun provideGameRepository(dao: GameDao): GameRepository = GameRepositoryImpl(dao = dao)

    @Provides
    fun provideCreateGameUseCase(repository: GameRepository): CreateGameUseCase =
        CreateGameUseCaseImpl(gameRepository = repository)
}
