package com.artemy.example.di

import com.artemy.payback.BuildConfig
import com.artemy.example.data.remote.PixabayService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
	@Provides
	fun provideOkHttpClient(): OkHttpClient {
		val okHttpBuilder = OkHttpClient.Builder()
		if (BuildConfig.DEBUG) {
			val loggingInterceptor = HttpLoggingInterceptor()
			loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
			okHttpBuilder.addInterceptor(loggingInterceptor)
		}

		return okHttpBuilder
			.readTimeout(60, TimeUnit.SECONDS)
			.connectTimeout(60, TimeUnit.SECONDS)
			.writeTimeout(60, TimeUnit.SECONDS)
			.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build()
	}

	@Singleton
	@Provides
	fun provideGson(): Gson {
		return GsonBuilder()
			.setLenient()
			.create()
	}

	@Singleton
	@Provides
	fun providePixabayService(retrofit: Retrofit): PixabayService {
		return retrofit.create(PixabayService::class.java)
	}
}