package com.faqrulans.yourgames.di

import com.faqrulans.core.domain.usecase.DeveloperUseCase
import com.faqrulans.core.domain.usecase.GameUseCase
import com.faqrulans.core.domain.usecase.impl.DeveloperUseCaseImpl
import com.faqrulans.core.domain.usecase.impl.GameUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @AppScope
    @Binds
    abstract fun provideDeveloperUseCase(developerUseCaseImpl: DeveloperUseCaseImpl): DeveloperUseCase

    @Binds
    abstract fun provideGameUseCase(gameUseCaseImpl: GameUseCaseImpl): GameUseCase

}
