package app.appworks.school.stylish.data.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import app.appworks.school.stylish.data.*
import app.appworks.school.stylish.data.collected.CollectedFormat
import app.appworks.school.stylish.data.collected.ProductCollected
import app.appworks.school.stylish.data.source.StylishDataSource
import app.appworks.school.stylish.data.subscribe.Email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Concrete implementation of a Stylish source as a db.
 */
class StylishLocalDataSource(val context: Context) : StylishDataSource {

    override suspend fun getMarketingHots(): Result<List<HomeItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getProductList(type: String, paging: String?): Result<ProductListResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUserProfile(token: String): Result<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun userSignIn(fbToken: String): Result<UserSignInResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun checkoutOrder(
        token: String, orderDetail: OrderDetail): Result<CheckoutOrderResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun subscribeNews(
        email: Email): Result<PostResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertUserCollected(
        collectedFormat: CollectedFormat ): Result<PostResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteUserCollected(
        collectedFormat: CollectedFormat ): Result<PostResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getEverydayPoint(
        getPoint: GetPoint): Result<ReceivePoint> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProductsCart(): LiveData<List<Product>> {
        return StylishDatabase.getInstance(context).stylishDatabaseDao.getAllProductsCart()
    }

    override fun getProductsCollected(): LiveData<List<ProductCollected>> {
        return StylishDatabase.getInstance(context).stylishDatabaseDao.getAllProductsCollected()
    }

    override suspend fun isProductCart(id: Long, colorCode: String, size: String): Boolean {
        return withContext(Dispatchers.IO) {
            StylishDatabase.getInstance(context).stylishDatabaseDao.getCart(id, colorCode, size) != null
        }
    }

    override suspend fun isProductCollected(id: Long): Boolean {
        return withContext(Dispatchers.IO) {
            StylishDatabase.getInstance(context).stylishDatabaseDao.getCollected(id) != null
        }
    }

    override suspend fun insertProductCart(product: Product) {
        withContext(Dispatchers.IO) {
            StylishDatabase.getInstance(context).stylishDatabaseDao.insertCart(product)
        }
    }

    override suspend fun insertProductCollect(productCollected: ProductCollected) {
        withContext(Dispatchers.IO) {
            StylishDatabase.getInstance(context).stylishDatabaseDao.insertCollected(productCollected)
        }
    }

    override suspend fun updateProductCart(product: Product) {
        withContext(Dispatchers.IO) {
            StylishDatabase.getInstance(context).stylishDatabaseDao.update(product)
        }
    }

    override suspend fun removeProductCart(id: Long, colorCode: String, size: String) {
        withContext(Dispatchers.IO) {
            StylishDatabase.getInstance(context).stylishDatabaseDao.deleteCart(id, colorCode, size)
        }
    }

    override suspend fun removeProductCollect(id: Long) {
        withContext(Dispatchers.IO) {
            StylishDatabase.getInstance(context).stylishDatabaseDao.deleteCollected(id)
        }
    }

    override suspend fun clearProductCart() {
        withContext(Dispatchers.IO) {
            StylishDatabase.getInstance(context).stylishDatabaseDao.clearCart()
        }
    }

    override suspend fun clearProductCollect() {
        withContext(Dispatchers.IO) {
            StylishDatabase.getInstance(context).stylishDatabaseDao.clearCollected()
        }
    }

    override suspend fun getUserInformation(key: String?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
