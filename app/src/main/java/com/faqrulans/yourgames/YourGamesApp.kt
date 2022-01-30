package com.faqrulans.yourgames

import android.app.Application
import com.faqrulans.yourgames.di.AppComponent
import com.faqrulans.yourgames.di.DaggerAppComponent

class YourGamesApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create()
    }

}
