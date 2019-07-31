package app.appworks.school.stylish.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Wayne Chen in Jul. 2019.
 */
@Parcelize
data class OrderCheckoutProperty(
    val data: OrderSuccess? = null,
    val error: String? = null
) : Parcelable

@Parcelize
data class OrderSuccess(
    val number: String
) : Parcelable