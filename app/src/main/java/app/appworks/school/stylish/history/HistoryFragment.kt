package app.appworks.school.stylish.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.Observer
import app.appworks.school.stylish.NavigationDirections
import app.appworks.school.stylish.databinding.FragmentHistoryBinding
import app.appworks.school.stylish.ext.getVmFactory

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
        binding.recyclerCart.adapter = HistoryAdapter(viewModel)

        viewModel.navigateToComment.observe(this, Observer {
            it?.let {
                findNavController().navigate(NavigationDirections.navigateToCommentFragment(it))
                viewModel.onCommentNavigated()
            }
        })


        binding.layoutSwipeRefreshCart.setOnRefreshListener {
            binding.recyclerCart.adapter?.notifyDataSetChanged()
            binding.layoutSwipeRefreshCart.isRefreshing = false
        }

        return binding.root
    }
}