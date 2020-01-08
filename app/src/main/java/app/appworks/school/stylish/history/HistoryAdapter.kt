package app.appworks.school.stylish.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.appworks.school.stylish.data.OrderResult
import app.appworks.school.stylish.databinding.ItemCheckoutHistoryBinding

class HistoryAdapter(val viewModel: HistoryViewModel, private val onClickListener: OnClickListener): ListAdapter<OrderResult, HistoryAdapter.ProductViewHolder>(DiffCallback) {

    class OnClickListener(val clickListener: (orderResult: OrderResult) -> Unit) {
        fun onClick(orderResult: OrderResult) = clickListener(orderResult)
    }

    class ProductViewHolder(private var binding: ItemCheckoutHistoryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: OrderResult, viewModel: HistoryViewModel, onClickListener: OnClickListener) {

            binding.product = product
            binding.viewModel = viewModel
            binding.root.setOnClickListener { onClickListener.onClick(product) }
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<OrderResult>() {
        override fun areItemsTheSame(oldItem: OrderResult, newItem: OrderResult): Boolean {
            return (oldItem.commentId == newItem.commentId)
        }

        override fun areContentsTheSame(oldItem: OrderResult, newItem: OrderResult): Boolean {
            return (oldItem.hasComment == newItem.hasComment)
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
        holder.bind(getItem(position), viewModel, onClickListener)
    }
}