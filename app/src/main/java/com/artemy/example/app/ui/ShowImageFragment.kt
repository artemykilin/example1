package com.artemy.example.app.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.artemy.payback.R
import com.artemy.payback.databinding.ShowImageFragmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShowImageFragment @Inject constructor(): DaggerFragment(R.layout.show_image_fragment) {
	@Inject lateinit var viewModel: ShowImageFragmentViewModel
	private lateinit var binding: ShowImageFragmentBinding
	private val args: ShowImageFragmentArgs by navArgs()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding = ShowImageFragmentBinding.bind(view)
		binding.vm = viewModel

		viewLifecycleOwner.lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.CREATED) {
				viewModel.loadImageDetails(args.imageId)
			}
		}

		viewModel.imageUrlData.observe(viewLifecycleOwner) {
			Glide.with(binding.root)
				.load(it)
				.apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
				.into(binding.image)
		}
	}
}