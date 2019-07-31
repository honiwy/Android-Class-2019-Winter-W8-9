package app.appworks.school.stylish.data

import android.os.Parcelable
import com.squareup.moshi.Json
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
    val picture: String
) : Parcelable
