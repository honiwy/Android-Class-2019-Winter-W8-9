package app.appworks.school.stylish.store

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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

enum class Branch(val positionOnSpinner: Int) {
    TAIWAN(0),
    AUSTRALIA(1),
    FINLAND(2),
    MOROCCO(3)
}
class StoreViewModel(private val stylishRepository: StylishRepository) : ViewModel() {


    val selectedStorePosition = MutableLiveData<Int>()
    val branchName: LiveData<Branch> = Transformations.map(selectedStorePosition) {
        Branch.values()[it]
    }


    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")
    }

}