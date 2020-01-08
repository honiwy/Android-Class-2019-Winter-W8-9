package app.appworks.school.stylish.comment

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import app.appworks.school.stylish.R
import app.appworks.school.stylish.StylishApplication
import app.appworks.school.stylish.data.OrderResult
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.collected.CollectedFormat
import app.appworks.school.stylish.data.collected.ProductCollected
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.login.UserManager
import app.appworks.school.stylish.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CommentViewModel(
    private val stylishRepository: StylishRepository,
    private val arguments: OrderResult
) : ViewModel() {

    private val _orderResult = MutableLiveData<OrderResult>().apply {
        value = arguments
    }

    val orderResult: LiveData<OrderResult>
        get() = _orderResult

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _leaveComment = MutableLiveData<Boolean>()

    val leaveComment: LiveData<Boolean>
        get() = _leaveComment
}