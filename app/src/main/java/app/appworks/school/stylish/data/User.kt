package app.appworks.school.stylish.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Wayne Chen in Jul. 2019.
 */
@Parcelize
data class User(
    val id: Int,
    val provider: String,
    val name: String,
    val email: String,
    val picture: String,
    var gotTodayPoint: Boolean = false
) : Parcelable
