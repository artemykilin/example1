package com.artemy.example.app

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

interface IAppDispatchers {
	val main: MainCoroutineDispatcher
	val default: CoroutineDispatcher
	val io: CoroutineDispatcher
	val unconfined: CoroutineDispatcher
}

@Singleton
class AppDispatchers @Inject constructor() : IAppDispatchers {
	override val main: MainCoroutineDispatcher = Dispatchers.Main
	override val default: CoroutineDispatcher = Dispatchers.Default
	override val io: CoroutineDispatcher = Dispatchers.IO
	override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}