package app.appworks.school.stylish.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.data.OrderResult
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.login.UserManager
import app.appworks.school.stylish.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import app.appworks.school.stylish.data.Result

class HistoryViewModel(private val stylishRepository: StylishRepository) : ViewModel() {

//    // Get products from database to provide count number to bottom badge of cart
//    val products: LiveData<List<Product>> = stylishRepository.getProductsInCart()


    private val _orderResults = MutableLiveData<List<OrderResult>>()

    val orderResults: LiveData<List<OrderResult>>
        get() = _orderResults


    private val _navigateToComment = MutableLiveData<OrderResult>()

    val navigateToComment: LiveData<OrderResult>
        get() = _navigateToComment

    // status for the loading icon of swl
    private val _refreshStatus = MutableLiveData<Boolean>()

    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus

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
        getOrderResult()
    }

    private fun getOrderResult() {

        coroutineScope.launch {
            // It will return Result object after Deferred flow
            val result = stylishRepository.getOrderList(UserManager.userId)
            _orderResults.value = when (result) {
                is Result.Success -> {
                    result.data.orderProducts
                }
                is Result.Error -> {
                    null
                }
                else -> {
                    null
                }
            }
        }
    }

    fun navigateToComment(orderResult: OrderResult) {

        _navigateToComment.value = orderResult
    }

    fun onCommentNavigated() {
        _navigateToComment.value = null
    }


}