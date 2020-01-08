package app.appworks.school.stylish.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Wayne Chen in Jul. 2019.
 */
@Parcelize
data class GetPoint(
    @Json(name = "userId") val userId: Int,
    @Json(name = "todayPoint") val todayPoint: Int
) : Parcelable