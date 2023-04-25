package com.artemy.example.di

import android.app.Application
import android.content.Context
import com.artemy.example.app.AppDispatchers
import com.artemy.example.app.IAppDispatchers
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
	@Binds
	abstract fun bindContext(application: Application): Context

	@Binds
	abstract fun bindAppDispatchers(dispatchers: AppDispatchers): IAppDispatchers
}