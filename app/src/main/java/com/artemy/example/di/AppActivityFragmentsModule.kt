package com.artemy.example.di

import com.artemy.example.app.ui.ImageListFragment
import com.artemy.example.app.ui.ShowImageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivityFragmentsModule {
	@FragmentScoped
	@ContributesAndroidInjector
	abstract fun provideImageListFragment(): ImageListFragment

	@FragmentScoped
	@ContributesAndroidInjector
	abstract fun provideShowImageFragment(): ShowImageFragment
}