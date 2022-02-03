package com.faqrulans.core.di

import com.faqrulans.core.data.DeveloperRepositoryImpl
import com.faqrulans.core.data.GameRepositoryImpl
import com.faqrulans.core.domain.repository.DeveloperRepository
import com.faqrulans.core.domain.repository.GameRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideDeveloperRepository(
        developerRepositoryImpl: DeveloperRepositoryImpl
    ): DeveloperRepository

    @Binds
    abstract fun provideGameRepository(
        gameRepositoryImpl: GameRepositoryImpl
    ): GameRepository

}
