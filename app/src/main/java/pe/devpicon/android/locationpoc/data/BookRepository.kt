package pe.devpicon.android.locationpoc.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ExperimentalCoroutinesApi
class BookRepository {
    fun getBooks(): Flow<List<BookResponse>> {
        return flow {
            val books = RestApi.bookService.getBooks().books
            emit(books)
        }.flowOn(Dispatchers.IO)
    }
}