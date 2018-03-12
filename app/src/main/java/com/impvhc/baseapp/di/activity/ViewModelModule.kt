package com.impvhc.baseapp.di.activity

import android.arch.lifecycle.ViewModel
import com.impvhc.baseapp.ui.level.LevelViewModel
import com.impvhc.baseapp.ui.main.MainViewModel
import com.impvhc.baseapp.ui.splash.SplashViewModel
import com.impvhc.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by victor on 2/13/18.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LevelViewModel::class)
    internal abstract fun bindLevelViewModel(levelViewModel: LevelViewModel): ViewModel
}