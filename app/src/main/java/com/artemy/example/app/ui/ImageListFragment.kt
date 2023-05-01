package com.artemy.example.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artemy.example.R
import com.artemy.example.databinding.ImageListFragmentBinding
import com.artemy.example.databinding.ImageListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageListFragment @Inject constructor() : DaggerFragment(R.layout.image_list_fragment) {
	@Inject lateinit var viewModel: ImageListFragmentViewModel
	private lateinit var binding: ImageListFragmentBinding

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding = ImageListFragmentBinding.bind(view)

		binding.vm = viewModel
		val adapter = ImageListAdapter(ImageDetailsComparator)
		binding.recyclerView.adapter = adapter
		binding.recyclerView.layoutManager = LinearLayoutManager(context)

		binding.startSearchButton.setOnClickListener { v ->
			context?.let {
				val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
				imm.hideSoftInputFromWindow(v.windowToken, 0)
			}
			adapter.refresh()
		}

		viewLifecycleOwner.lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.CREATED) {
				viewModel
					.getPagingDataFlow()
					.collectLatest { pagingData ->
						adapter.submitData(pagingData)
					}
			}
		}

		viewLifecycleOwner.lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.CREATED) {
				adapter.loadStateFlow.collectLatest { loadStates ->
					binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
					if (loadStates.refresh is LoadState.Error) {
						val state = loadStates.refresh as LoadState.Error
						Snackbar.make(
							binding.root,
							state.error.localizedMessage ?: "null",
							Snackbar.LENGTH_LONG
						).show()
					}

					if (loadStates.refresh is LoadState.NotLoading) {
						binding.recyclerView.isVisible = adapter.itemCount > 0
						binding.noContent.isVisible = adapter.itemCount == 0
					}
				}
			}
		}
	}

	private fun openImage(imageId: Int) {
		findNavController().navigate(ImageListFragmentDirections.actionShowImage(imageId))
	}

	inner class ImageListAdapter(callback: DiffUtil.ItemCallback<UiImageDetailsShort>):
		PagingDataAdapter<UiImageDetailsShort, ImageListAdapter.ItemViewHolder>(callback) {
		inner class ItemViewHolder(private val binding: ImageListItemBinding): RecyclerView.ViewHolder(binding.root) {
			fun bind(item: UiImageDetailsShort?) {
				item?.let {
					Glide.with(binding.root)
						.load(it.thumbnailUrl)
						.apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
						.into(binding.thumbnail)
					binding.userName.text = getString(R.string.uploaded_by, it.username)
					binding.itemTags.text = getString(R.string.tags, it.tags)
					binding.root.setOnClickListener { openImage(item.id) }
				}
			}
		}

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
			return ItemViewHolder(
				ImageListItemBinding.inflate(
					LayoutInflater.from(parent.context),
					parent,
					false
				)
			)
		}

		override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
			val item = getItem(position)
			holder.bind(item)
		}
	}

	object ImageDetailsComparator : DiffUtil.ItemCallback<UiImageDetailsShort> () {
		override fun areItemsTheSame(oldItem: UiImageDetailsShort, newItem: UiImageDetailsShort): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: UiImageDetailsShort, newItem: UiImageDetailsShort): Boolean {
			return oldItem == newItem
		}
	}
}

