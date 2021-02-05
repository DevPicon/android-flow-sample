package pe.devpicon.android.locationpoc.data

import com.google.gson.annotations.SerializedName

data class BooksResponse(
        @SerializedName(value = "books") val books: List<BookResponse>
)

data class BookResponse(
        @SerializedName(value = "name") val name: String,
        @SerializedName(value = "price") val price: Double,
        @SerializedName(value = "quantity") val quantity: Int
)