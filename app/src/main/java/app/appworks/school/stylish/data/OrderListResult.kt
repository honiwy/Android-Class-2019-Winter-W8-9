package app.appworks.school.stylish.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderListResult(
    @Json(name = "data") val orderProducts: List<OrderResult>? = null
) : Parcelable