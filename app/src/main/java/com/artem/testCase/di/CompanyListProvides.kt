package com.artem.testCase.di

import com.artem.testCase.retrofit.ApiRequest
import com.artem.testCase.retrofit.LifehackApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class CompanyListProvides {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson): LifehackApi {
        return Retrofit.Builder()
            .baseUrl("https://lifehack.studio/test_task/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(LifehackApi::class.java)

    }

    @Provides
    fun providesApiRequest(): ApiRequest {
        return ApiRequest()
    }

}