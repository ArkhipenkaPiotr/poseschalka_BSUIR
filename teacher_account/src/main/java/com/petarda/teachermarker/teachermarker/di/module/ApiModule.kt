package com.petarda.teachermarker.teachermarker.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.petarda.teachermarker.teachermarker.BuildConfig
import com.petarda.teachermarker.teachermarker.api.JournalApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    @Provides
    @Singleton
    fun provideJournalApi(retrofit: Retrofit): JournalApi =
            retrofit.create(JournalApi::class.java)
}