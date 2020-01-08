package app.appworks.school.stylish.ext

import androidx.fragment.app.Fragment
import app.appworks.school.stylish.StylishApplication
import app.appworks.school.stylish.catalog.CatalogTypeFilter
import app.appworks.school.stylish.data.OrderResult
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.User
import app.appworks.school.stylish.factory.*

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Extension functions for Fragment.
 */
fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as StylishApplication).stylishRepository
    return ViewModelFactory(repository)
}

fun Fragment.getVmFactory(user: User?): ProfileViewModelFactory {
    val repository = (requireContext().applicationContext as StylishApplication).stylishRepository
    return ProfileViewModelFactory(repository, user)
}

fun Fragment.getVmFactory(product: Product): ProductViewModelFactory {
    val repository = (requireContext().applicationContext as StylishApplication).stylishRepository
    return ProductViewModelFactory(repository, product)
}

fun Fragment.getVmFactory(orderResult: OrderResult): OrderResultViewModelFactory {
    val repository = (requireContext().applicationContext as StylishApplication).stylishRepository
    return OrderResultViewModelFactory(repository, orderResult)
}

fun Fragment.getVmFactory(catalogType: CatalogTypeFilter): CatalogItemViewModelFactory {
    val repository = (requireContext().applicationContext as StylishApplication).stylishRepository
    return CatalogItemViewModelFactory(repository, catalogType)
}