package com.faqrulans.favorite.di

import androidx.lifecycle.ViewModel
import com.faqrulans.favorite.FavoriteViewModel
import com.faqrulans.yourgames.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModelModule(viewModel: FavoriteViewModel): ViewModel

}
