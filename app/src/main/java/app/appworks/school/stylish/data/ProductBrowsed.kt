package app.appworks.school.stylish.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import app.appworks.school.stylish.data.source.local.StylishConverters
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "products_in_browsed_table", primaryKeys = ["product_id"])
@TypeConverters(StylishConverters::class)
@Parcelize
data class ProductBrowsed(
    @ColumnInfo(name = "product_id")
    val id: Long,
    @ColumnInfo(name = "product_title")
    val title: String,
    @ColumnInfo(name = "product_description")
    val description: String,
    @ColumnInfo(name = "product_price")
    val price: Int,
    @ColumnInfo(name = "product_texture")
    val texture: String,
    @ColumnInfo(name = "product_wash")
    val wash: String,
    @ColumnInfo(name = "product_place")
    val place: String,
    @ColumnInfo(name = "product_note")
    val note: String,
    @ColumnInfo(name = "product_story")
    val story: String,
    @ColumnInfo(name = "product_colors")
    val colors: List<Color>,
    @ColumnInfo(name = "product_sizes")
    val sizes: List<String>,
    @ColumnInfo(name = "product_variants")
    val variants: List<Variant>,
    @ColumnInfo(name = "product_main_image")
    @Json(name = "main_image") val mainImage: String,
    @ColumnInfo(name = "product_images")
    val images: List<String>
) : Parcelable