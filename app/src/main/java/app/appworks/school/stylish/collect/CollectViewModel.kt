package app.appworks.school.stylish.collect

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.ProductCollected
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CollectViewModel(private val stylishRepository: StylishRepository) : ViewModel() {

    val products: LiveData<List<ProductCollected>> = stylishRepository.getProductsCollected()

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

    fun removeProduct(productCollected: ProductCollected) {
        Log.i("apple","Remove product collected")
        coroutineScope.launch {
            stylishRepository.removeProductCollected(productCollected.id)
        }
    }

}