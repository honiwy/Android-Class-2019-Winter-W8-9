package app.appworks.school.stylish.collect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.appworks.school.stylish.cart.CartAdapter
import app.appworks.school.stylish.cart.CartFragmentDirections
import app.appworks.school.stylish.cart.CartViewModel
import app.appworks.school.stylish.databinding.FragmentCartBinding
import app.appworks.school.stylish.databinding.FragmentCollectBinding
import app.appworks.school.stylish.ext.getVmFactory

class CollectFragment : Fragment() {

    /**
     * Lazily initialize our [CartViewModel].
     */
    private val viewModel by viewModels<CollectViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        init()
        val binding = FragmentCollectBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerCart.adapter = CollectAdapter(viewModel)

        binding.layoutSwipeRefreshCart.setOnRefreshListener {
            binding.recyclerCart.adapter?.notifyDataSetChanged()
            binding.layoutSwipeRefreshCart.isRefreshing = false
        }

        return binding.root
    }
}