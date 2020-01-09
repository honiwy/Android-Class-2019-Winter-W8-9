package app.appworks.school.stylish.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Map(
    val storeName:String,
    val storeTime: String,
    val storeLocation: String,
    val storeGPS: String,
    var storePhone: String
) : Parcelable