package app.appworks.school.stylish.collect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.appworks.school.stylish.data.collected.ProductCollected
import app.appworks.school.stylish.databinding.ItemCollectBinding

class CollectAdapter(val viewModel: CollectViewModel, private val onClickListener: OnClickListener ): ListAdapter<ProductCollected, CollectAdapter.ProductViewHolder>(DiffCallback) {

    class OnClickListener(val clickListener: (productCollected: ProductCollected) -> Unit) {
        fun onClick(productCollected: ProductCollected) = clickListener(productCollected)
    }

    class ProductViewHolder(private var binding: ItemCollectBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductCollected, viewModel: CollectViewModel, onClickListener: OnClickListener) {

            binding.product = product
            binding.viewModel = viewModel
            binding.root.setOnClickListener { onClickListener.onClick(product) }
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ProductCollected>() {
        override fun areItemsTheSame(oldItem: ProductCollected, newItem: ProductCollected): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(oldItem: ProductCollected, newItem: ProductCollected): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ItemCollectBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel, onClickListener)
    }
}