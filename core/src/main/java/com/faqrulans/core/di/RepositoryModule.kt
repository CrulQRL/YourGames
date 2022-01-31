package com.faqrulans.core.di

import com.faqrulans.core.data.DeveloperRepositoryImpl
import com.faqrulans.core.domain.repository.DeveloperRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(
        developerRepositoryImpl: DeveloperRepositoryImpl
    ): DeveloperRepository

}
