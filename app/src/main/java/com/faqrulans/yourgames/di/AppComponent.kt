package com.faqrulans.yourgames.di

import com.faqrulans.core.di.CoreComponent
import com.faqrulans.yourgames.detail.DeveloperDetailFragment
import com.faqrulans.yourgames.home.HomeFragment
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
}
