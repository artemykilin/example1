package com.artemy.example.di

import com.artemy.example.MyTestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
	TestAppModule::class,
	TestDataModule::class,
	TestNetworkModule::class,
	AppActivityBindingModule::class,
	AppActivityFragmentsModule::class,
	AndroidInjectionModule::class,
	ViewModelModule::class
]
)
interface TestAppComponent : AndroidInjector<MyTestApplication> {
	@Component.Builder
	interface Builder {
		@BindsInstance
		fun application(application: MyTestApplication) : TestAppComponent.Builder
		fun build() : TestAppComponent
	}

}