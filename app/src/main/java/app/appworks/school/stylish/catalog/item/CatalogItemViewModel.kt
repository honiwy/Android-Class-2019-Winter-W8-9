package app.appworks.school.stylish.catalog.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import app.appworks.school.stylish.R
import app.appworks.school.stylish.StylishApplication
import app.appworks.school.stylish.catalog.CatalogTypeFilter
import app.appworks.school.stylish.component.GridSpacingItemDecoration
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.network.LoadApiStatus
import app.appworks.school.stylish.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * The [ViewModel] that is attached to the [CatalogItemFragment].
 */
class CatalogItemViewModel(private val stylishRepository: StylishRepository) : ViewModel() {

    // Handle the type for each catalog item
    var catalogType: CatalogTypeFilter = CatalogTypeFilter.ACCESSORIES

    private val sourceFactory by lazy {
        PagingDataSourceFactory(catalogType)
    }

    val pagingDataProducts: LiveData<PagedList<Product>> by lazy {
        sourceFactory.toLiveData(6, null)
    }

    // Handle load api status
    val status: LiveData<LoadApiStatus> by lazy {
        Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.statusInitialLoad
        }
    }

    // Handle load api error
    val error: LiveData<String> by lazy {
        Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.errorInitialLoad
        }
    }

    // Handle navigation to detail
    private val _navigateToDetail = MutableLiveData<Product>()

    val navigateToDetail: LiveData<Product>
        get() = _navigateToDetail

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val decoration = GridSpacingItemDecoration(
        2,
        StylishApplication.instance.resources.getDimensionPixelSize(R.dimen.space_catalog_grid), true
    )

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
    }

    fun navigateToDetail(product: Product) {
        _navigateToDetail.value = product
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }
}