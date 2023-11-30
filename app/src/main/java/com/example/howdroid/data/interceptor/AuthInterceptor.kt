package com.example.howdroid.data.interceptor

import com.example.howdroid.data.datasource.local.HowDroidStorage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val howDroidStorage: HowDroidStorage,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val isAutoLogin = howDroidStorage.isLogin

        val authRequestBuilder = originalRequest.newBuilder()
        if (isAutoLogin) {
            authRequestBuilder.addHeader(ACCESS_TOKEN, howDroidStorage.accessToken)
        } else {
            authRequestBuilder.removeHeader(ACCESS_TOKEN)
        }

        val authRequest = authRequestBuilder.build()

        val response = chain.proceed(authRequest)

        when (response.code) {
            401 -> {
                // TODO: 토큰 재발급 API 연동
            }
        }
        return response
    }

    companion object {
        const val ACCESS_TOKEN = "Authorization"
    }
}
