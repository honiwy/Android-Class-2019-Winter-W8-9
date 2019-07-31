package app.appworks.school.stylish.data

/**
 * Created by Wayne Chen in Jul. 2019.
 */
sealed class HotsDataItem {

    abstract val id: Long

    data class TitleItem(val title: String): HotsDataItem() {
        override val id: Long = -1
    }
    data class FullProductItem(val product: Product): HotsDataItem() {
        override val id: Long
            get() = product.id
    }
    data class CollageProductItem(val product: Product): HotsDataItem() {
        override val id: Long
            get() = product.id
    }
}