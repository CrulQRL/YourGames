package com.faqrulans.yourgames.di

import com.faqrulans.yourgames.home.HomeFragment
import dagger.Component

@AppScope
@Component(
//    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun inject(fragment: HomeFragment)
}
