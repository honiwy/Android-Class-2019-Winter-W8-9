package app.appworks.school.stylish.collect

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.StylishApplication
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.collected.ProductCollected
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

    // Handle navigation to detail
    private val _navigateToDetail = MutableLiveData<Product>()

    val navigateToDetail: LiveData<Product>
        get() = _navigateToDetail


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
            Toast.makeText(StylishApplication.instance,"取消收藏", Toast.LENGTH_SHORT).show()
            stylishRepository.removeProductCollected(productCollected.id)
        }
    }

    fun navigateToDetail(productCollected: ProductCollected) {
        val product= Product(productCollected.id,productCollected.title,productCollected.description,productCollected.price,productCollected.texture,productCollected.wash,productCollected.place,
            productCollected.note,productCollected.story,productCollected.colors,productCollected.sizes,productCollected.variants,productCollected.mainImage,productCollected.images)
        _navigateToDetail.value = product
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }
}