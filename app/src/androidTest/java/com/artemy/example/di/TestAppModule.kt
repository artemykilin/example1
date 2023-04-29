package com.artemy.example.di

import android.content.Context
import com.artemy.example.MyTestApplication
import com.artemy.example.app.AppDispatchers
import com.artemy.example.app.IAppDispatchers
import dagger.Binds
import dagger.Module

@Module
abstract class TestAppModule {
	@Binds
	abstract fun bindContext(application: MyTestApplication): Context

	@Binds
	abstract fun bindAppDispatchers(dispatchers: AppDispatchers): IAppDispatchers
}