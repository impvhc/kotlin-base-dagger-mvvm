package com.impvhc.api.di

import com.impvhc.api.BuildConfig
import com.impvhc.api.util.DateDeserializer
import com.impvhc.api.util.ErrorHandlingExecutorCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by victor on 2/13/18.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun providesGson(): Gson {
        return GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .create()
    }

    @Provides
    @Singleton
    internal fun providesAdapter(gson: Gson): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(45, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(45, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(ErrorHandlingExecutorCallAdapterFactory(ErrorHandlingExecutorCallAdapterFactory.MainThreadExecutor()))
                .build()
    }

    @Provides
    @Singleton
    internal fun providesRequestInterceptor(): Interceptor {
        return getDeviceLocale(BuildConfig.VERSION_CODE.toString()) //not real version
    }

    fun getDeviceLocale(appVersion: String): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val headers = request.headers().newBuilder()
            headers.add("Accept-Language", Locale.getDefault().language)
            headers.add("AppVersion", appVersion)
            request = request.newBuilder().headers(headers.build()).build()
            chain.proceed(request)
        }
    }

}
