package app.appworks.school.stylish.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.Observer
import app.appworks.school.stylish.NavigationDirections
import app.appworks.school.stylish.R
import app.appworks.school.stylish.databinding.FragmentHistoryBinding
import app.appworks.school.stylish.ext.getVmFactory
import app.appworks.school.stylish.network.HistoryFilter

class HistoryFragment : Fragment() {

    private val viewModel by viewModels<HistoryViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerCart.adapter = HistoryAdapter(viewModel, HistoryAdapter.OnClickListener {
            viewModel.navigateToComment(it)
        })

        viewModel.navigateToComment.observe(this, Observer {
            it?.let {
                findNavController().navigate(NavigationDirections.navigateToCommentFragment(it))
                viewModel.onCommentNavigated()
            }
        })


        viewModel.refreshStatus.observe(this, Observer {
            it?.let {
                binding.layoutSwipeRefreshCart.isRefreshing = it
            }
        })


        binding.layoutSwipeRefreshCart.setOnRefreshListener {
            binding.recyclerCart.adapter?.notifyDataSetChanged()
            binding.layoutSwipeRefreshCart.isRefreshing = false
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Updates the filter in the [HistoryViewModel] when the menu items are selected from the
     * overflow menu.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_comment_yet_menu -> HistoryFilter.SHOW_COMMENT_YET
                R.id.show_commented_menu -> HistoryFilter.SHOW_COMMENTED
                else -> HistoryFilter.SHOW_ALL
            }
        )
        return true
    }
}