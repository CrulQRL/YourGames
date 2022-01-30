package com.faqrulans.yourgames.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faqrulans.core.ui.ViewModelFactory
import com.faqrulans.yourgames.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @AppScope
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
