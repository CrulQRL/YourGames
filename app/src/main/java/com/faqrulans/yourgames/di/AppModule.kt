package com.faqrulans.yourgames.di

import com.faqrulans.core.domain.usecase.DeveloperUseCase
import com.faqrulans.core.domain.usecase.GameUseCase
import com.faqrulans.core.domain.usecase.impl.DeveloperUserCaseImpl
import com.faqrulans.core.domain.usecase.impl.GameUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideDeveloperUseCase(developerUserCaseImpl: DeveloperUserCaseImpl): DeveloperUseCase

    @Binds
    abstract fun provideGameUseCase(gameUseCaseImpl: GameUseCaseImpl): GameUseCase

}
