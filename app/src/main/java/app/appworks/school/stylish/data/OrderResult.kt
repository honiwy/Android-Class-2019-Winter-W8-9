package app.appworks.school.stylish.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderResult(
    val orderNumber:String,
    val amount: Int,
    val title: String,
    val colorName: String,
    val size: String,
    val productId: String,
    val mainImage: String,
    val commentId: Int,
    val star: Int,
    val comment: String,
    var hasComment: Boolean
) : Parcelable