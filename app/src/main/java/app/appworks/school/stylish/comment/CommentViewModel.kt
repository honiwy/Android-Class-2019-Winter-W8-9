package app.appworks.school.stylish.comment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.data.OrderResult
import app.appworks.school.stylish.data.Result
import app.appworks.school.stylish.data.comment.Comment
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.login.UserManager
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

    val hasCommented = MutableLiveData<Boolean>().apply {
        value = arguments.hasComment
    }

    val star = MutableLiveData<Int>().apply {
        if(arguments.hasComment)
           value = arguments.star
    }
    val comment = MutableLiveData<String>().apply {
        if(arguments.hasComment)
            value = arguments.comment
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

    fun uploadComment() {
        coroutineScope.launch {
            // It will return Result object after Deferred flow
            val result = stylishRepository.uploadComment(
                Comment(
                    UserManager.userId!!,
                    arguments.commentId,
                    star.value!!,
                    comment.value!!
                )
            )
            Log.i("apple", "result: $result")

            hasCommented.value = when (result) {
                is Result.Success -> {
                    true
                }
                else -> {
                    false
                }
            }

            arguments.hasComment = hasCommented.value ?: false
        }


        Log.i(
            "apple",
            "star: ${star.value}; comment: ${comment.value}; commentId: ${orderResult.value!!.commentId};" +
                    "userId: ${UserManager.userId}"
        )
    }
}