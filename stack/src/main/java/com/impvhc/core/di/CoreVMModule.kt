package com.impvhc.core.di

import android.arch.lifecycle.ViewModelProvider
import com.impvhc.core.CoreVMFactory
import dagger.Binds
import dagger.Module

/**
 * Created by victor on 2/13/18.
 */
@Module
abstract class CoreVMModule {
    @Binds
    internal abstract fun bindCoreVMFactory(factory: CoreVMFactory): ViewModelProvider.Factory
}