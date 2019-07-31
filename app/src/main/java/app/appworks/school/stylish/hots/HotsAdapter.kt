package app.appworks.school.stylish.hots

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.appworks.school.stylish.data.HotsDataItem
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.databinding.ItemHotsCollageBinding
import app.appworks.school.stylish.databinding.ItemHotsFullBinding
import app.appworks.school.stylish.databinding.ItemHotsTitleBinding
import app.appworks.school.stylish.util.Logger

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * [HotsDataItem], including computing diffs between lists.
 * @param onClickListener a lambda that takes the
 */
class HotsAdapter(private val onClickListener: OnClickListener ) :
        ListAdapter<HotsDataItem, RecyclerView.ViewHolder>(DiffCallback) {
    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Product]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Product]
     */
    class OnClickListener(val clickListener: (product: Product) -> Unit) {
        fun onClick(product: Product) = clickListener(product)
    }

    class TitleViewHolder(private var binding: ItemHotsTitleBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {

            binding.title = title
            binding.executePendingBindings()
        }
    }

    class FullProductViewHolder(private var binding: ItemHotsFullBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, onClickListener: OnClickListener) {

            binding.product = product
            binding.root.setOnClickListener { onClickListener.onClick(product) }
            binding.executePendingBindings()
        }
    }

    class CollageProductViewHolder(private var binding: ItemHotsCollageBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, onClickListener: OnClickListener) {

            binding.product = product
            binding.root.setOnClickListener { onClickListener.onClick(product) }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<HotsDataItem>() {
        override fun areItemsTheSame(oldItem: HotsDataItem, newItem: HotsDataItem): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: HotsDataItem, newItem: HotsDataItem): Boolean {
            return oldItem.id == newItem.id
        }

        private const val ITEM_VIEW_TYPE_TITLE            = 0x00
        private const val ITEM_VIEW_TYPE_PRODUCT_FULL     = 0x01
        private const val ITEM_VIEW_TYPE_PRODUCT_COLLAGE  = 0x02
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_TITLE -> TitleViewHolder(ItemHotsTitleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false))
            ITEM_VIEW_TYPE_PRODUCT_FULL -> FullProductViewHolder(ItemHotsFullBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false))
            ITEM_VIEW_TYPE_PRODUCT_COLLAGE -> CollageProductViewHolder(ItemHotsCollageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false))
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is TitleViewHolder -> {
                holder.bind((getItem(position) as HotsDataItem.TitleItem).title)
            }
            is FullProductViewHolder -> {
                holder.bind((getItem(position) as HotsDataItem.FullProductItem).product, onClickListener)
            }
            is CollageProductViewHolder -> {
                holder.bind((getItem(position) as HotsDataItem.CollageProductItem).product, onClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HotsDataItem.TitleItem -> ITEM_VIEW_TYPE_TITLE
            is HotsDataItem.FullProductItem -> ITEM_VIEW_TYPE_PRODUCT_FULL
            is HotsDataItem.CollageProductItem -> ITEM_VIEW_TYPE_PRODUCT_COLLAGE
        }
    }
}
