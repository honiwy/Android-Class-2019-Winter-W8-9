package app.appworks.school.stylish.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize

/**
 * Created by Wayne Chen in Jul. 2019.
 */
@Parcelize
data class Color(
    val name: String,
    val code: String
) : Parcelable
