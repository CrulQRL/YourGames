package com.faqrulans.core.di

import android.content.Context
import com.faqrulans.core.domain.repository.DeveloperRepository
import com.faqrulans.core.domain.repository.GameRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideDeveloperRepository() : DeveloperRepository

    fun provideGameRepository() : GameRepository
}
