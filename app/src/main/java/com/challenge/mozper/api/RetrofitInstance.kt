package com.challenge.mozper.api

import com.challenge.mozper.base.Constants.API_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

class RetrofitInstance {
    companion object {
        val BUILDER: Retrofit by lazy {
            val (ssl, trustManager) = getUnsafeOkHttpClient()
            val client = OkHttpClient.Builder()
                .sslSocketFactory(ssl, trustManager)
                .hostnameVerifier { _, _ -> true }
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
            Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        private val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
            object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<X509Certificate>?,
                    authType: String?
                ) {
                    // no-op
                }

                override fun checkServerTrusted(
                    chain: Array<X509Certificate>?,
                    authType: String?
                ) {
                    // no-op
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )

        private fun getUnsafeOkHttpClient() = try {

            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

            Pair(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}