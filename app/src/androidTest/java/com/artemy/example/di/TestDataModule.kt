package com.artemy.example.di

import com.artemy.example.data.Repository
import com.artemy.example.domain.IRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [TestNetworkModule::class, LocalStorageModule::class])
abstract class TestDataModule {
	@Binds
	@Singleton
	abstract fun provideRepository(reposition: Repository): IRepository
}