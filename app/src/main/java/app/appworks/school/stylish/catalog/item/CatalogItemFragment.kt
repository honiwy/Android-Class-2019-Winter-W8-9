package app.appworks.school.stylish.catalog.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.appworks.school.stylish.NavigationDirections
import app.appworks.school.stylish.catalog.CatalogTypeFilter
import app.appworks.school.stylish.databinding.FragmentCatalogItemBinding
import app.appworks.school.stylish.ext.getVmFactory

/**
 * Created by Wayne Chen in Jul. 2019.
 */
class CatalogItemFragment : Fragment() {

    /**
     * Lazily initialize our [CatalogItemViewModel].
     */
    private val viewModel by viewModels<CatalogItemViewModel> { getVmFactory() }

    var catalogType: CatalogTypeFilter = CatalogTypeFilter.ACCESSORIES

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentCatalogItemBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this@CatalogItemFragment
        viewModel.let {
            it.catalogType = catalogType
            binding.viewModel = it
        }

        binding.recyclerCatalogItem.adapter = PagingAdapter(PagingAdapter.OnClickListener {
            viewModel.navigateToDetail(it)
        })

        viewModel.navigateToDetail.observe(this, Observer {
            it?.let {
                findNavController().navigate(NavigationDirections.navigateToDetailFragment(it))
                viewModel.onDetailNavigated()
            }
        })

        viewModel.pagingDataProducts.observe(this@CatalogItemFragment, Observer {
            (binding.recyclerCatalogItem.adapter as PagingAdapter).submitList(it)
        })

        return binding.root
    }
}