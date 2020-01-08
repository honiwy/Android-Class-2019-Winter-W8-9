package app.appworks.school.stylish.data.source

import androidx.lifecycle.LiveData
import app.appworks.school.stylish.data.*
import app.appworks.school.stylish.data.collected.CollectedFormat
import app.appworks.school.stylish.data.collected.ProductCollected
import app.appworks.school.stylish.data.subscribe.Email
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Concrete implementation to load Stylish sources.
 */
class DefaultStylishRepository(private val stylishRemoteDataSource: StylishDataSource,
                               private val stylishLocalDataSource: StylishDataSource,
                               private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : StylishRepository {

    override suspend fun getMarketingHots(): Result<List<HomeItem>> {
        return stylishRemoteDataSource.getMarketingHots()
    }

    override suspend fun getProductList(type: String, paging: String?): Result<ProductListResult> {
        return stylishRemoteDataSource.getProductList(type = type, paging = paging)
    }

    override suspend fun getOrderList(userId: Int?): Result<OrderListResult> {
        return stylishRemoteDataSource.getOrderList(userId = userId)
    }

    override suspend fun getUserProfile(token: String): Result<User> {
        return stylishRemoteDataSource.getUserProfile(token)
    }

    override suspend fun userSignIn(fbToken: String): Result<UserSignInResult> {
        return stylishRemoteDataSource.userSignIn(fbToken)
    }

    override suspend fun checkoutOrder(
        token: String, orderDetail: OrderDetail): Result<CheckoutOrderResult> {
        return stylishRemoteDataSource.checkoutOrder(token, orderDetail)
    }

    override suspend fun subscribeNews(
        email: Email ): Result<PostResult> {
        return stylishRemoteDataSource.subscribeNews(email)
    }

    override suspend fun insertUserCollected(
        collectedFormat: CollectedFormat): Result<PostResult> {
        return stylishRemoteDataSource.insertUserCollected(collectedFormat)
    }

    override suspend fun deleteUserCollected(
        collectedFormat: CollectedFormat): Result<PostResult> {
        return stylishRemoteDataSource.deleteUserCollected(collectedFormat)
    }

    override  fun getProductsInCart(): LiveData<List<Product>> {
        return stylishLocalDataSource.getProductsInCart()
    }

    override  fun getProductsCollected(): LiveData<List<ProductCollected>> {
        return stylishLocalDataSource.getProductsCollected()
    }

    override suspend fun isProductInCart(id: Long, colorCode: String, size: String): Boolean {
        return stylishLocalDataSource.isProductCart(id, colorCode, size)
    }

    override suspend fun isProductCollected(id: Long): Boolean {
        return stylishLocalDataSource.isProductCollected(id)
    }

    override suspend fun insertProductInCart(product: Product) {
        stylishLocalDataSource.insertProductCart(product)
    }

    override suspend fun insertProductCollected(productCollected: ProductCollected) {
        stylishLocalDataSource.insertProductCollect(productCollected)
    }

    override suspend fun updateProductInCart(product: Product) {
        stylishLocalDataSource.updateProductCart(product)
    }

    override suspend fun removeProductInCart(id: Long, colorCode: String, size: String) {
        stylishLocalDataSource.removeProductCart(id, colorCode, size)
    }

    override suspend fun removeProductCollected(id: Long) {
        stylishLocalDataSource.removeProductCollect(id)
    }

    override suspend fun clearProductInCart() {
        stylishLocalDataSource.clearProductCart()
    }

    override suspend fun clearProductCollected() {
        stylishLocalDataSource.clearProductCollect()
    }

    override suspend fun getUserInformation(key: String?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
