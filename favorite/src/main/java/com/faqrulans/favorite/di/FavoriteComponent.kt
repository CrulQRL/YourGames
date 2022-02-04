package com.faqrulans.favorite.di

import com.faqrulans.favorite.FavoriteFragment
import com.faqrulans.yourgames.di.AppComponent
import dagger.Component

@FavoriteScope
@Component(
    dependencies = [AppComponent::class],
    modules = [FavoriteModule::class]
)
interface FavoriteComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): FavoriteComponent
    }

    fun inject(favoriteFragment: FavoriteFragment)

}
