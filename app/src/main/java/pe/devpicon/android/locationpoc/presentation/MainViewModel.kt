package pe.devpicon.android.locationpoc.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import pe.devpicon.android.locationpoc.data.BookRepository

@ExperimentalCoroutinesApi
class MainViewModel(
        private val bookRepository: BookRepository,
        private val mappers: Mappers
) : ViewModel() {

    private val _books: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val books: StateFlow<ScreenState> = _books.asStateFlow()

    fun load() = viewModelScope.launch {
        _books.value = ScreenState.Loading
        delay(5000)
        bookRepository.getBooks()
                .map { value -> value.map(mappers::fromDataToPresentation) }
                .collect { _books.value = ScreenState.Ready(it) }
    }

}

sealed class ScreenState {
    object Loading : ScreenState()
    class Ready(val books: List<Book>) : ScreenState()
}

@ExperimentalCoroutinesApi
class CustomViewModelFactory(
        private val repository: BookRepository,
        private val mappers: Mappers
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                    bookRepository = repository,
                    mappers = mappers
            ) as T
            else -> error("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}