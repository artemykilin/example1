package com.artemy.example.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.artemy.payback.databinding.ShowImageFragmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShowImageFragment @Inject constructor(): DaggerFragment() {
	@Inject lateinit var viewModel: ShowImageFragmentViewModel
	private lateinit var binding: ShowImageFragmentBinding
	val args: ShowImageFragmentArgs by navArgs()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = ShowImageFragmentBinding.inflate(inflater)
		binding.vm = viewModel

		viewLifecycleOwner.lifecycleScope.launch {
			viewModel.loadImageDetails(args.imageId)
		}

		viewModel.imageUrlData.observe(
			viewLifecycleOwner,
			{
				Glide.with(binding.root)
					.load(it)
					.apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
					.into(binding.image)
			}
		)

		return binding.root
	}
}