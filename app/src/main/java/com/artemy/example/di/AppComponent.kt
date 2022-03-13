package com.artemy.example.di

import android.app.Application
import com.artemy.example.app.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AppModule::class,
		DataModule::class,
		AppActivityBindingModule::class,
		AppActivityFragmentsModule::class,
		AndroidInjectionModule::class,
		ViewModelModule::class
	]
)
interface AppComponent: AndroidInjector<MyApplication> {
	@Component.Builder
	interface Builder {
		@BindsInstance
		fun application(application : Application) : AppComponent.Builder
		fun build() : AppComponent
	}
}