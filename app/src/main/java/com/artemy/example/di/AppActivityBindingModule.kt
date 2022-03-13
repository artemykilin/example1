package com.artemy.example.di

import com.artemy.example.app.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivityBindingModule {
	@ActivityScoped
	@ContributesAndroidInjector (modules = [AppActivityFragmentsModule::class])
	abstract fun providesMainActivity(): MainActivity
}