package app.appworks.school.stylish.detail

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
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.collected.CollectedFormat
import app.appworks.school.stylish.data.collected.ProductCollected
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import app.appworks.school.stylish.login.UserManager

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * The [ViewModel] that is attached to the [DetailFragment].
 */
class DetailViewModel(
    private val stylishRepository: StylishRepository,
    private val arguments: Product
) : ViewModel() {

    // Detail has product data from arguments
    private val _product = MutableLiveData<Product>().apply {
        value = arguments
    }

    val product: LiveData<Product>
        get() = _product

    val productSizesText: LiveData<String> = Transformations.map(product) {
        when (it.sizes.size) {
            0 -> ""
            1 -> it.sizes.first()
            else -> StylishApplication.instance.getString(R.string._dash_, it.sizes.first(), it.sizes.last())
        }
    }

    // it for gallery circles design
    private val _snapPosition = MutableLiveData<Int>()

    val snapPosition: LiveData<Int>
        get() = _snapPosition

    // Handle navigation to Add2cart
    private val _navigateToAdd2cart = MutableLiveData<Product>()

    val navigateToAdd2cart: LiveData<Product>
        get() = _navigateToAdd2cart

    // Handle leave detail
    private val _leaveDetail = MutableLiveData<Boolean>()

    val leaveDetail: LiveData<Boolean>
        get() = _leaveDetail

    private val _collectProduct = MutableLiveData<Boolean>()

    val collectProduct: LiveData<Boolean>
        get() = _collectProduct

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val decoration = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.left = 0
            } else {
                outRect.left = StylishApplication.instance.resources.getDimensionPixelSize(R.dimen.space_detail_circle)
            }
        }
    }

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")
        coroutineScope.launch {
            _collectProduct.value = stylishRepository.isProductCollected(arguments.id)
        }
    }

    /**
     * When the gallery scroll, at the same time circles design will switch.
     */
    fun onGalleryScrollChange(layoutManager: RecyclerView.LayoutManager?, linearSnapHelper: LinearSnapHelper) {
        val snapView = linearSnapHelper.findSnapView(layoutManager)
        snapView?.let {
            layoutManager?.getPosition(snapView)?.let {
                if (it != snapPosition.value) {
                    _snapPosition.value = it
                }
            }
        }
    }

    fun navigateToAdd2cart(product: Product) {
        _navigateToAdd2cart.value = product
    }

    fun onAdd2cartNavigated() {
        _navigateToAdd2cart.value = null
    }

    fun leaveDetail() {
        updateServerCollection()
        _leaveDetail.value = true
    }

    fun collectProduct(){
        _collectProduct.value = !(_collectProduct.value!!)
        Log.i("apple","collect product? ${_collectProduct.value}")
        product.value?.let {
            coroutineScope.launch {
                if (_collectProduct.value == true) {
                    Log.i("apple", "add local collection")
                    Toast.makeText(StylishApplication.instance,"加入收藏",Toast.LENGTH_SHORT).show()
                    stylishRepository.insertProductCollected(
                        ProductCollected( it.id, it.title, it.description, it.price,
                            it.texture, it.wash,it.place,it.note,it.story,
                            it.colors, it.sizes,it.variants, it.mainImage, it.images
                        )
                    )
//                    Log.i("apple","update server collection")
//                    stylishRepository.insertUserCollected(CollectedFormat(UserManager.userId!!,product.value!!.id))
                }
                else {
                    Log.i("apple", "remove")
                    Toast.makeText(StylishApplication.instance,"取消收藏",Toast.LENGTH_SHORT).show()
                    stylishRepository.removeProductCollected(it.id)
//                    Log.i("apple","delete server collection")
//                    stylishRepository.deleteUserCollected(CollectedFormat(UserManager.userId!!,product.value!!.id))
                }
            }
        }
    }
    fun updateServerCollection(){
        coroutineScope.launch {
            if (_collectProduct.value == true) {
                Log.i("apple","update server collection")
                stylishRepository.insertUserCollected(CollectedFormat(UserManager.userId!!,product.value!!.id))
            }
            else {
                Log.i("apple","delete server collection")
                stylishRepository.deleteUserCollected(CollectedFormat(UserManager.userId!!,product.value!!.id))
            }
        }
    }
}