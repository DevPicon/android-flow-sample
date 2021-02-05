package pe.devpicon.android.locationpoc.data

import retrofit2.http.GET

interface BookService {

    @GET("/api/books")
    suspend fun getBooks(): BooksResponse
}