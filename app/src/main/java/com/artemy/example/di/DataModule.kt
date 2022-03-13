package com.artemy.example.di

import com.artemy.example.data.Repository
import com.artemy.example.domain.IRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, LocalStorageModule::class])
abstract class DataModule {
	@Binds
	@Singleton
	abstract fun provideRepository(reposition: Repository): IRepository
}