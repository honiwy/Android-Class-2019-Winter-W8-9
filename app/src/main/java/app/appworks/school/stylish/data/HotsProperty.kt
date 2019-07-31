package app.appworks.school.stylish.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Wayne Chen in Jul. 2019.
 */
@Parcelize
data class HotsProperty(
    @Json(name = "data") val hotsList: List<Hots>
) : Parcelable {

    fun getDataItems(): List<HotsDataItem> {
        val items = ArrayList<HotsDataItem>()

        for ((title, hots) in hotsList) {
            items.add(HotsDataItem.TitleItem(title))
            for ((index, product) in hots.withIndex()) {
                when (index % 2) {
                    0 -> items.add(HotsDataItem.FullProductItem(product))
                    1 -> items.add(HotsDataItem.CollageProductItem(product))
                }
            }
        }
        return items
    }
}