package app.appworks.school.stylish.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HistoryViewModel(private val stylishRepository: StylishRepository) : ViewModel() {

    // Get products from database to provide count number to bottom badge of cart
    val products: LiveData<List<Product>> = stylishRepository.getProductsInCart()


    private val _navigateToComment = MutableLiveData<Product>()

    val navigateToComment: LiveData<Product>
        get() = _navigateToComment

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")
    }

    fun navigateToComment(product: Product) {

        _navigateToComment.value = product
    }

    fun onCommentNavigated() {
        _navigateToComment.value = null
    }


}