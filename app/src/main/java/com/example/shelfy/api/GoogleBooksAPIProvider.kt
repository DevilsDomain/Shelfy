package com.example.shelfy.api

import com.example.shelfy.model.BooksResult
import com.example.shelfy.model.cb.GoogleBooksResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val BASE_URL = "https://www.googleapis.com/books/v1/"

class GoogleBooksAPIProvider {

    private val retrofit by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create<GoogleBooksAPI>()
    }

    class GoogleBooksAPIProvider {

        private val retrofit by lazy {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create<GoogleBooksAPI>()
        }

        fun searchBooks(query: String, apiKey: String, callback: GoogleBooksResult) {
            retrofit.searchBooks(query, apiKey)
                .enqueue(object : Callback<BooksResult> {
                    override fun onResponse(call: Call<BooksResult>, response: Response<BooksResult>) {
                        if (response.isSuccessful) {
                            callback.onDataFetchedSuccess(response.body()?.items ?: emptyList())
                        } else {
                            callback.onDataFetchedFailed()
                        }
                    }

                    override fun onFailure(call: Call<BooksResult>, t: Throwable) {
                        callback.onDataFetchedFailed()
                    }
                })
        }
    }
}


