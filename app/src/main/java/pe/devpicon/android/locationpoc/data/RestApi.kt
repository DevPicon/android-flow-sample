package pe.devpicon.android.locationpoc.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApi {
    private val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.9:4000")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val bookService = retrofit.create(BookService::class.java)
}