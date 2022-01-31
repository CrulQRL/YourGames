package com.faqrulans.yourgames

import android.app.Application
import com.faqrulans.core.di.CoreComponent
import com.faqrulans.core.di.DaggerCoreComponent
import com.faqrulans.yourgames.di.AppComponent
import com.faqrulans.yourgames.di.DaggerAppComponent

class YourGamesApp : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }

}
