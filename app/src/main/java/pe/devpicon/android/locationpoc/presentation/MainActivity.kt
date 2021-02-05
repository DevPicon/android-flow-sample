package pe.devpicon.android.locationpoc.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import pe.devpicon.android.locationpoc.data.BookRepository
import pe.devpicon.android.locationpoc.databinding.ActivityMainBinding

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainViewBinding.root)

        mainViewModel = ViewModelProvider(this, CustomViewModelFactory(
                repository = BookRepository(),
                mappers = Mappers()
        ))[MainViewModel::class.java]

        lifecycleScope.launchWhenStarted {
            mainViewModel.books.collect {
                when (it) {
                    is ScreenState.Loading -> {
                        mainViewBinding.tvResult.text = "Loading"
                    }
                    is ScreenState.Ready -> {
                        mainViewBinding.tvResult.text = it.books.toString()
                    }
                }

            }
        }

        mainViewModel.load()
    }
}