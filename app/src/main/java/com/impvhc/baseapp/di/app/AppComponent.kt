package com.impvhc.baseapp.di.app

import android.app.Application
import com.impvhc.api.di.ApiModule
import com.impvhc.baseapp.App
import com.impvhc.baseapp.di.activity.ActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by victor on 2/13/18.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, ApiModule::class, ActivityModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun api(apiModule: ApiModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}