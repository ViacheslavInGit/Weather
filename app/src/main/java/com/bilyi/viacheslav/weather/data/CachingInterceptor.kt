package com.bilyi.viacheslav.weather.data

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CachingInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Chain): Response =
        chain.proceed(chain.createRequestWithCacheControl())

    companion object {

        private val DEFAULT_STALE_SECONDS = TimeUnit.MINUTES.toSeconds(10)

        private fun Chain.createRequestWithCacheControl(): Request =
            request()
                .newBuilder()
                .addHeader(
                    "Cache-Control",
                    "private, max-stale=$DEFAULT_STALE_SECONDS"
                )
                .build()
    }
}