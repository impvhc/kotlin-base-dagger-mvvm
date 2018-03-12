package com.impvhc.baseapp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.impvhc.api.di.ApiModule
import com.impvhc.baseapp.di.app.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by victor on 2/13/18.
 */
class App : Application() , HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        //Instantiate Dagger
        DaggerAppComponent.builder()
                .application(this)
                .api(ApiModule())
                .build()
                .inject(this)

        //Register activity lifeCycle callback listener for automatically injecting every activity
        //that launches
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks() {
            override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
                p0?.let { AndroidInjection.inject(p0) }
            }
        })
    }


    abstract class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(p0: Activity?) {
        }

        override fun onActivityResumed(p0: Activity?) {
        }

        override fun onActivityStarted(p0: Activity?) {
        }

        override fun onActivityDestroyed(p0: Activity?) {
        }

        override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
        }

        override fun onActivityStopped(p0: Activity?) {
        }

        override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        }
    }
}