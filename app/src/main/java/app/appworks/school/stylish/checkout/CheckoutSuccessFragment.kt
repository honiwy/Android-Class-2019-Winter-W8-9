package app.appworks.school.stylish.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import app.appworks.school.stylish.MainViewModel
import app.appworks.school.stylish.databinding.FragmentCheckoutSuccessBinding
import app.appworks.school.stylish.ext.getVmFactory
import app.appworks.school.stylish.history.HistoryFragmentDirections
import app.appworks.school.stylish.profile.ProfileFragmentDirections

/**
 * Created by Wayne Chen in Jul. 2019.
 */
class CheckoutSuccessFragment : Fragment() {

    /**
     * Lazily initialize our [CheckoutSuccessViewModel].
     */
    private val viewModel by viewModels<CheckoutSuccessViewModel> { getVmFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        init()
        val binding = FragmentCheckoutSuccessBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.navigateToHome.observe(this, Observer {
            it?.let {
                val mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
                mainViewModel.navigateToHomeByBottomNav()
                viewModel.onHomeNavigated()
            }
        })

        viewModel.navigateToHistory.observe(this, Observer {
            it?.let {
                findNavController().navigate(CheckoutSuccessFragmentDirections.navigateToHistoryFragment())
                viewModel.onHistoryNavigated()
            }
        })

        // Handle back key behavior to navigate to Home
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
                mainViewModel.navigateToHomeByBottomNav()
            }
        })

        return binding.root
    }

//    private fun init() {
//        activity?.let {
//            ViewModelProviders.of(it).get(MainViewModel::class.java).apply {
//                currentFragmentType.value = CurrentFragmentType.CHECKOUT_SUCCESS
//            }
//        }
//    }
}