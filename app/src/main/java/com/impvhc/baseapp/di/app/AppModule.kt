package com.impvhc.baseapp.di.app

import android.app.Application
import com.impvhc.baseapp.AppSharedPreferences
import com.impvhc.baseapp.di.activity.ViewModelModule
import com.impvhc.core.di.CoreVMModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by victor on 2/13/18.
 */
@Module(includes = arrayOf(CoreVMModule::class, ViewModelModule::class))
internal class AppModule {
    @Singleton
    @Provides
    fun providesAppSharedPreferences(app: Application): AppSharedPreferences {
        return AppSharedPreferences(app)
    }
}