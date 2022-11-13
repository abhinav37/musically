package com.abhinav.musically.network

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

// User agent class as MusicBrainz throws 403 if empty
class UserAgentInterceptor : Interceptor {
    private val userAgent: String =
        "Musically/v1.1" + "(com.abhinav.musically;build:10000 Android SDK 33) Agent"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestWithUserAgent = request.newBuilder()
            .header(USER_AGENT, userAgent)
            .build()

        return chain.proceed(requestWithUserAgent)
    }

    companion object {
        private const val USER_AGENT = "User-Agent"
    }
}
