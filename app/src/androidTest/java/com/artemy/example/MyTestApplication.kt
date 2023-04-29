package com.artemy.example

import com.artemy.example.di.DaggerTestAppComponent
import com.artemy.example.di.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyTestApplication: DaggerApplication() {
	override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
		return DaggerTestAppComponent.builder().application(this).build()
	}
}