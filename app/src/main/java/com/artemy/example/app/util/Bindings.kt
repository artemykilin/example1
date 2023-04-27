package com.artemy.example.app.util

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController

object Bindings {
	@BindingAdapter("isVisible")
	@JvmStatic
	fun View.bindIsVisible(visible: Boolean?) {
		isVisible = visible == true
	}
}

fun NavController.safeNavigate(@IdRes destination: Int, args: Bundle) {
	currentDestination?.getAction(destination)?.run { navigate(destination, args) }
}
