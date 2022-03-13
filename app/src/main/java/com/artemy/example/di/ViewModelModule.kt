package com.artemy.example.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artemy.example.app.ui.ImageListFragmentViewModel
import com.artemy.example.app.ui.ShowImageFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
	@Binds
	abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

	@Binds
	@IntoMap
	@ViewModelKey(ImageListFragmentViewModel::class)
	abstract fun imageListFragmentViewModel(viewModel: ImageListFragmentViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(ShowImageFragmentViewModel::class)
	abstract fun showImageFragmentViewModel(viewModel: ShowImageFragmentViewModel): ViewModel

}