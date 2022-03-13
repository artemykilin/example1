package com.artemy.example.app

import com.artemy.example.di.AppComponent
import com.artemy.example.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication: DaggerApplication() {

	private lateinit var mAppComponent: AppComponent

	override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
		mAppComponent = DaggerAppComponent.builder().application(this).build()
		return mAppComponent
	}
}