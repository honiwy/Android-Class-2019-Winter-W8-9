package app.appworks.school.stylish.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.appworks.school.stylish.MainViewModel
import app.appworks.school.stylish.cart.CartViewModel
import app.appworks.school.stylish.checkout.CheckoutSuccessViewModel
import app.appworks.school.stylish.collect.CollectViewModel
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.dialog.SubscribeViewModel
import app.appworks.school.stylish.history.HistoryViewModel
import app.appworks.school.stylish.home.HomeViewModel
import app.appworks.school.stylish.login.LoginViewModel
import app.appworks.school.stylish.payment.PaymentViewModel
import app.appworks.school.stylish.store.StoreViewModel

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val stylishRepository: StylishRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(stylishRepository)

                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(stylishRepository)

                isAssignableFrom(CartViewModel::class.java) ->
                    CartViewModel(stylishRepository)

                isAssignableFrom(StoreViewModel::class.java) ->
                    StoreViewModel(stylishRepository)

                isAssignableFrom(PaymentViewModel::class.java) ->
                    PaymentViewModel(stylishRepository)

                isAssignableFrom(CollectViewModel::class.java) ->
                    CollectViewModel(stylishRepository)

                isAssignableFrom(HistoryViewModel::class.java) ->
                    HistoryViewModel(stylishRepository)

                isAssignableFrom(SubscribeViewModel::class.java) ->
                    SubscribeViewModel(stylishRepository)

                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(stylishRepository)

                isAssignableFrom(CheckoutSuccessViewModel::class.java) ->
                    CheckoutSuccessViewModel(stylishRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
