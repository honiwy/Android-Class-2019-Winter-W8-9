package app.appworks.school.stylish.dialog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SubscribeViewModel : ViewModel() {

    val emailTyped = MutableLiveData<String>()


    fun checkEmailAvailable(): Boolean{
        var boo = false
        emailTyped.value?.let{email->
            boo = email.contains("@")
        }
        return boo
    }
    fun subscribeNews(){
       Log.i("apple","subscribe success")
    }
}