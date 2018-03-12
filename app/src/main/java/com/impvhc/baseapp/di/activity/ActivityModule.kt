package com.impvhc.baseapp.di.activity

import com.impvhc.baseapp.ui.level.LevelActivity
import com.impvhc.baseapp.ui.main.MainActivity
import com.impvhc.baseapp.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by victor on 2/13/18.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun contributeLevelActivity(): LevelActivity
}