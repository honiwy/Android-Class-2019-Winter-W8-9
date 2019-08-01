package app.appworks.school.stylish.data.source.remote

import androidx.lifecycle.LiveData
import app.appworks.school.stylish.R
import app.appworks.school.stylish.data.*
import app.appworks.school.stylish.data.source.StylishDataSource
import app.appworks.school.stylish.network.StylishApi
import app.appworks.school.stylish.util.Logger
import app.appworks.school.stylish.util.Util.getString
import app.appworks.school.stylish.util.Util.isInternetConnected

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Implementation of the Stylish source that from network.
 */
object StylishRemoteDataSource : StylishDataSource {

    override suspend fun getMarketingHots(): Result<List<HomeItem>> {

        if (!isInternetConnected()) {
            return Result.Fail(getString(R.string.internet_not_connected))
        }
        // Get the Deferred object for our Retrofit request
        val getResultDeferred = StylishApi.retrofitService.getMarketingHots()
        return try {
            // this will run on a thread managed by Retrofit
            val listResult = getResultDeferred.await()

            listResult.error?.let {
                return Result.Fail(it)
            }
            Result.Success(listResult.toHomeItems())

        } catch (e: Exception) {
            Logger.w("[${this::class.simpleName}] exception=${e.message}")
            Result.Error(e)
        }
    }

    override suspend fun getProductList(type: String, paging: String?): Result<ProductListProperty> {
        val getPropertiesDeferred = StylishApi.retrofitService.getProductList(type = type, paging = paging)
        return try {

            val listResult = getPropertiesDeferred.await()
            Result.Success(listResult)

        } catch (e: Exception) {

            Result.Error(e)
        }
    }

    override suspend fun getUserProfile(token: String): Result<User> {
        val getPropertiesDeferred = StylishApi.retrofitService.getUserProfile(token)
        return try {

            val listResult = getPropertiesDeferred.await()
            Result.Success(listResult.user)

        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun userSignIn(fbToken: String): Result<UserSignInProperty> {
        val getPropertiesDeferred = StylishApi.retrofitService.userSignIn(fbToken = fbToken)
        return try {

            val listResult = getPropertiesDeferred.await()
            Result.Success(listResult)

        } catch (e: Exception) {

            Result.Error(e)
        }
    }

    override suspend fun postOrderCheckout(
        token: String, orderCheckoutDetail: OrderCheckoutDetail): Result<OrderCheckoutProperty> {
        val getPropertiesDeferred
                = StylishApi.retrofitService.postOrderCheckout(token, orderCheckoutDetail)
        return try {

            val listResult = getPropertiesDeferred.await()
            when {
                listResult.data != null -> Result.Success(listResult)
                listResult.error != null -> Result.Fail(listResult.error)
                else -> Result.Fail("LRU19")
            }
        } catch (e: Exception) {

            Result.Error(e)
        }
    }

    override fun getProductsInCart(): LiveData<List<Product>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun isProductInCart(id: Long, colorCode: String, size: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertProductInCart(product: Product) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateProductInCart(product: Product) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun removeProductInCart(id: Long, colorCode: String, size: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun clearProductInCart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUserInformation(key: String?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
