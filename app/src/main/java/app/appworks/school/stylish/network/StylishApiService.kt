package app.appworks.school.stylish.network

import app.appworks.school.stylish.BuildConfig
import app.appworks.school.stylish.data.*
import app.appworks.school.stylish.data.collected.CollectedFormat
import app.appworks.school.stylish.data.subscribe.Email
import app.appworks.school.stylish.data.PostResult
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

/**
 * Created by Wayne Chen in Jul. 2019.
 */
private const val HOST_NAME = "practicestylish.com"//"api.appworks-school.tw"
private const val API_VERSION = "1.0"
private const val BASE_URL = "https://$HOST_NAME/api/$API_VERSION/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = when (BuildConfig.LOGGER_VISIABLE) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
    })
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

/**
 * A public interface that exposes the [getMarketingHots], [getProductList], [getUserProfile],
 * [userSignIn], [checkoutOrder] methods
 */
interface StylishApiService {
    /**
     * Returns a Coroutine [Deferred] [MarketingHotsResult] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "marketing/hots" endpoint will be requested with the GET HTTP method
     */
    @GET("marketing/hots")
    fun getMarketingHots():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<MarketingHotsResult>
    /**
     * Returns a Coroutine [Deferred] [ProductListResult] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "products/{catalogType}" endpoint will be requested with the GET
     * HTTP method (catalogType: men, women, accessories)
     * The @Query annotation indicates that it will be added "?paging={pagingKey}" after endpoint
     */
    @GET("products/{catalogType}")
    fun getProductList(@Path("catalogType") type: String, @Query("paging") paging: String? = null):
            Deferred<ProductListResult>

    @GET("orderHistory")
    fun getOrderList(@Query("userId") userId: Int? = null):
            Deferred<OrderListResult>

    /**
     * Returns a Coroutine [Deferred] [UserProfileResult] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "user/profile" endpoint will be requested with the GET HTTP method
     * The @Header annotation indicates that it will be added "Authorization" header
     */
    @GET("user/profile")
    fun getUserProfile(@Header("Authorization") token: String):
            Deferred<UserProfileResult>


    /**
     * Returns a Coroutine [Deferred] [UserSignInResult] which can be fetched with await() if in a Coroutine scope.
     * The @POST annotation indicates that the "user/signin" endpoint will be requested with the POST HTTP method
     * The @Field annotation indicates that it will be added "provider", "access_token" key-pairs to the body of
     * the POST HTTP method, and it have to use @FormUrlEncoded to support @Field
     */
    @FormUrlEncoded
    @POST("user/signin")
    fun userSignIn(
        @Field("provider") provider: String = "facebook",
        @Field("access_token") fbToken: String):
            Deferred<UserSignInResult>
    /**
     * Returns a Coroutine [Deferred] [CheckoutOrderResult] which can be fetched with await() if in a Coroutine scope.
     * The @POST annotation indicates that the "user/signin" endpoint will be requested with the POST HTTP method
     * The @Header annotation indicates that it will be added "Authorization" header
     * The @Body annotation indicates that it will be added [OrderDetail] to the body of the POST HTTP method
     */
    @POST("order/checkout")
    fun checkoutOrder(@Header("Authorization") token: String, @Body orderDetail: OrderDetail):
            Deferred<CheckoutOrderResult>




    @POST("subscribe")
    fun subscribeEmail(@Body email: Email):
            Deferred<PostResult>

    @POST("userFavorite")
    fun insertUserCollected(@Body collectedFormat: CollectedFormat):
            Deferred<PostResult>

    //@DELETE("userFavorite") 因為有 body 所以不能用
    @HTTP(method = "DELETE",path = "userFavorite", hasBody = true)
    fun deleteUserCollected(@Body collectedFormat: CollectedFormat):
            Deferred<PostResult>

//
//    @PUT("addComment")
//    fun uploadComment(@Body collectedFormat: CollectedFormat):
//            Deferred<PostResult>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object StylishApi {
    val retrofitService : StylishApiService by lazy { retrofit.create(StylishApiService::class.java) }
}