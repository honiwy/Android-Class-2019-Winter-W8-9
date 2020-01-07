package app.appworks.school.stylish.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.databinding.ItemCheckoutHistoryBinding

class HistoryAdapter(val viewModel: HistoryViewModel ): ListAdapter<Product, HistoryAdapter.ProductViewHolder>(DiffCallback) {


    class ProductViewHolder(private var binding: ItemCheckoutHistoryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, viewModel: HistoryViewModel) {

            binding.product = product
            binding.viewModel = viewModel
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ItemCheckoutHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }
}