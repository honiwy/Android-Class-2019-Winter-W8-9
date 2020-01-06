package app.appworks.school.stylish.data.source

import androidx.lifecycle.LiveData
import app.appworks.school.stylish.data.*

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Main entry point for accessing Stylish sources.
 */
interface StylishDataSource {

    suspend fun getMarketingHots(): Result<List<HomeItem>>

    suspend fun getProductList(type: String, paging: String? = null): Result<ProductListResult>

    suspend fun getUserProfile(token: String): Result<User>

    suspend fun userSignIn(fbToken: String): Result<UserSignInResult>

    suspend fun checkoutOrder(token: String, orderDetail: OrderDetail): Result<CheckoutOrderResult>

    suspend fun subscribeNews(email:Email): Result<SubscribeResult>

    fun getProductsCart(): LiveData<List<Product>>

    fun getProductsCollected(): LiveData<List<ProductCollected>>

    suspend fun isProductCart(id: Long, colorCode: String, size: String): Boolean

    suspend fun isProductCollected(id: Long): Boolean

    suspend fun insertProductCart(product: Product)

    suspend fun insertProductCollect(productCollected: ProductCollected)

    suspend fun updateProductCart(product: Product)

    suspend fun removeProductCart(id: Long, colorCode: String, size: String)

    suspend fun removeProductCollect(id: Long)

    suspend fun clearProductCart()

    suspend fun clearProductCollect()

    suspend fun getUserInformation(key: String?): String
}
