package app.appworks.school.stylish.data.source

import androidx.lifecycle.LiveData
import app.appworks.school.stylish.data.*
import app.appworks.school.stylish.data.collected.CollectedFormat
import app.appworks.school.stylish.data.collected.ProductCollected
import app.appworks.school.stylish.data.subscribe.Email

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Interface to the Stylish layers.
 */
interface StylishRepository {

    suspend fun getMarketingHots(): Result<List<HomeItem>>

    suspend fun getProductList(type: String, paging: String? = null): Result<ProductListResult>

    suspend fun getUserProfile(token: String): Result<User>

    suspend fun userSignIn(fbToken: String): Result<UserSignInResult>

    suspend fun checkoutOrder(token: String, orderDetail: OrderDetail): Result<CheckoutOrderResult>

    suspend fun subscribeNews(email: Email): Result<PostResult>

    suspend fun insertUserCollected(collectedFormat: CollectedFormat): Result<PostResult>

    suspend fun deleteUserCollected(collectedFormat: CollectedFormat): Result<PostResult>

    suspend fun getEverydayPoint(getPoint: GetPoint):Result<ReceivePoint>

    fun getProductsInCart(): LiveData<List<Product>>

    fun getProductsCollected(): LiveData<List<ProductCollected>>

    suspend fun isProductInCart(id: Long, colorCode: String, size: String): Boolean

    suspend fun isProductCollected(id: Long): Boolean

    suspend fun insertProductInCart(product: Product)

    suspend fun insertProductCollected(productCollected: ProductCollected)

    suspend fun updateProductInCart(product: Product)

    suspend fun removeProductInCart(id: Long, colorCode: String, size: String)

    suspend fun removeProductCollected(id: Long)

    suspend fun clearProductInCart()

    suspend fun clearProductCollected()

    suspend fun getUserInformation(key: String?): String
}
