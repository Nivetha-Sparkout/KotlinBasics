package com.kotlinproject.app

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class BaseRetrofit {

//    AppHelper.Companion.getAge();

    val Retrofit: Retrofit? = null

    val StringReq: String? = "Request"
    val StringRes: String? = "Response"

    object foo {
        @JvmStatic
        val StringReq = "REQUEST"
        @JvmStatic
        val StringRes = "RESPONSE"
    }

    companion object {

        //        val BASE_URL = "https://simplifiedcoding.net/demos/"
        val BASE_URL = "http://18.218.156.72/truckwala/api/"
        var retrofit: Retrofit? = null
        fun getClient(): Retrofit? {
            if (retrofit == null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY


                val client = OkHttpClient.Builder().apply {
                    addInterceptor(interceptor)
                    addInterceptor { chain ->
                        var request = chain.request()
                        request = request.newBuilder()
                                .build()
                        val response = chain.proceed(request)
                        response
                    }
                }
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

            }
            return retrofit
        }

    }


}
