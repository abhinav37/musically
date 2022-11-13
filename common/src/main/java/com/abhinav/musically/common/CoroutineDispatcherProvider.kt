package com.abhinav.musically.common

import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers

/*
* Simple Dispatcher provider so test dispatchers can easily be switched when writing tests
*
* */
open class CoroutineDispatcherProvider @Inject constructor() {
    open val Main: CoroutineContext by lazy { Dispatchers.Main }
    open val IO: CoroutineContext by lazy { Dispatchers.IO }
}
