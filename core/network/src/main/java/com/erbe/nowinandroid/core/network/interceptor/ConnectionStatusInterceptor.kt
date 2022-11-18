package com.erbe.nowinandroid.core.network.interceptor

import com.erbe.nowinandroid.core.common.connection.ConnectionManager
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import javax.inject.Inject

class ConnectionStatusInterceptor @Inject constructor(
    private val connectionManager: ConnectionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected()) {
            chain.proceed(chain.request())
        } else {
            throw IOException("No network available")
        }
    }
}