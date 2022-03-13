package com.artemy.example.di

import android.content.Context
import androidx.room.Room
import com.artemy.example.data.local.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class LocalStorageModule {
	@Provides
	fun provideLocalStorage(context: Context): AppDatabase {
		return Room
			.databaseBuilder(context, AppDatabase::class.java, "cache-database")
			.fallbackToDestructiveMigration()
			.build()
	}
}