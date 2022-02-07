package com.faqrulans.yourgames.di

import androidx.lifecycle.ViewModelProvider
import com.faqrulans.core.di.CoreComponent
import com.faqrulans.core.domain.usecase.DeveloperUseCase
import com.faqrulans.yourgames.detail.DeveloperDetailFragment
import com.faqrulans.yourgames.home.HomeFragment
import com.faqrulans.yourgames.search.SearchFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)

    fun inject(fragment: DeveloperDetailFragment)

    fun inject(fragment: SearchFragment)

    fun provideViewModelFactory(): ViewModelProvider.Factory

    fun provideDeveloperUserCase(): DeveloperUseCase
}
