package app.appworks.school.stylish.dialog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SubscribeViewModel : ViewModel() {

    val emailTyped = MutableLiveData<String>()

    val isValidEmail = Transformations.map(emailTyped) {
        checkEmailAvailable()
    }

    private fun checkEmailAvailable(): Boolean{
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