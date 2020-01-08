package app.appworks.school.stylish.dialog

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.StylishApplication
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.data.subscribe.Email
import app.appworks.school.stylish.network.LoadApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscribeViewModel(private val stylishRepository: StylishRepository) : ViewModel() {

    val emailTyped = MutableLiveData<String>()

    val isValidEmail = Transformations.map(emailTyped) {
        checkEmailAvailable()
    }

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    // status: The internal MutableLiveData that stores the status of the most recent request

    val status = MutableLiveData<LoadApiStatus>()

    // Handle leave add2cart
    private val _leave = MutableLiveData<Boolean>()

    val leave: LiveData<Boolean>
        get() = _leave

    private fun checkEmailAvailable(): Boolean{
        var boo = false
        emailTyped.value?.let{email->
            boo = email.contains("@")
        }
        return boo
    }

    fun subscribeNews(){
        coroutineScope.launch {
            status.value = LoadApiStatus.LOADING
            // It will return Result object after Deferred flow
            val result = stylishRepository.subscribeNews(Email(emailTyped.value!!))
            status.value = LoadApiStatus.DONE
            Toast.makeText(StylishApplication.instance,"訂閱成功", Toast.LENGTH_SHORT).show()
            leave()
        }
    }

    fun leave() {
        _leave.value = true
    }

    fun onLeaveCompleted() {
        _leave.value = null
    }

}