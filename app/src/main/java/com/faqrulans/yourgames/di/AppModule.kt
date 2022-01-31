package com.faqrulans.yourgames.di

import com.faqrulans.core.domain.usecase.DeveloperUseCase
import com.faqrulans.core.domain.usecase.impl.DeveloperUserCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideDeveloperUseCase(developerUserCase: DeveloperUserCaseImpl): DeveloperUseCase

}
