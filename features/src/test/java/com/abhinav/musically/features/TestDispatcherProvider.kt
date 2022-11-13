package com.abhinav.musically.features

import com.abhinav.musically.common.CoroutineDispatcherProvider
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherProvider : CoroutineDispatcherProvider() {
    override val Main: CoroutineContext = UnconfinedTestDispatcher()
    override val IO: CoroutineContext = UnconfinedTestDispatcher()
}
