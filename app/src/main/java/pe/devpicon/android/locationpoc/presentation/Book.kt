package pe.devpicon.android.locationpoc.presentation

import pe.devpicon.android.locationpoc.data.BookResponse

data class Book(
        val name: String,
        val price: Double,
        val quantity: Int
)

class Mappers {
    fun fromDataToPresentation(bookResponse: BookResponse) = Book(
            bookResponse.name,
            bookResponse.price,
            bookResponse.quantity
    )
}