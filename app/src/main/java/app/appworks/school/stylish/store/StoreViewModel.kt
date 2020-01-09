package app.appworks.school.stylish.store

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.StylishApplication
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.Map
import app.appworks.school.stylish.data.collected.ProductCollected
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.login.UserManager
import app.appworks.school.stylish.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StoreViewModel(private val stylishRepository: StylishRepository) : ViewModel() {

    //val products: LiveData<List<ProductCollected>> = stylishRepository.getProductsCollected()

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // Handle navigation to map
    private val _navigateToMap = MutableLiveData<Map>()

    val navigateToMap: LiveData<Map>
        get() = _navigateToMap


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")
    }

    fun changeStore(storeIndex: Int) {

    }

    fun navigateToMap(mapInfo:Map) {
       //map method
        _navigateToMap.value = mapInfo
    }

    fun onMapNavigated() {
        _navigateToMap.value = null
    }
}