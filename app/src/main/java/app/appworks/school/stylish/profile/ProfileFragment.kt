package app.appworks.school.stylish.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.ViewModelProviders
import app.appworks.school.stylish.MainViewModel
import app.appworks.school.stylish.NavigationDirections
import app.appworks.school.stylish.R
import app.appworks.school.stylish.databinding.FragmentProfileBinding
import app.appworks.school.stylish.dialog.MessageDialog
import app.appworks.school.stylish.ext.getVmFactory

/**
 * Created by Wayne Chen in Jul. 2019.
 */
class ProfileFragment : Fragment() {

    /**
     * Lazily initialize our [ProfileViewModel].
     */
    private val viewModel by viewModels<ProfileViewModel> { getVmFactory(ProfileFragmentArgs.fromBundle(arguments!!).userKey) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        init()
        val binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        if (viewModel.user.value == null) {
            // user info will be null if user already logged in, and it will get user info from server,
            // here will show you how to set user info to MainViewModel
            val mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
            viewModel.user.observe(this, Observer {
                if (null != it) {
                    mainViewModel.setupUser(it)
                }
            })
        }

        viewModel.navigateToStore.observe(this, Observer {
            it?.let {
                findNavController().navigate(ProfileFragmentDirections.navigateToStoreFragment())
                viewModel.onStoreNavigated()
            }
        })

        viewModel.navigateToCollection.observe(this, Observer {
            it?.let {
                findNavController().navigate(ProfileFragmentDirections.navigateToCollectFragment())
                viewModel.onCollectionNavigated()
            }
        })

        viewModel.navigateToHistory.observe(this, Observer {
            it?.let {
                findNavController().navigate(NavigationDirections.navigateToHistoryFragment())
                viewModel.onHistoryNavigated()
            }
        })
        viewModel.navigateToAttended.observe(this, Observer {
            it?.let {
                var message = "本日已簽到"
                viewModel.totalPoint.value?.let {accumulatedPoint->
                    message += "\n累計: $accumulatedPoint 點"
                }
                findNavController().navigate(NavigationDirections.navigateToMessageDialog(
                    MessageDialog.MessageType.TOTALPOINT.apply { value.message = message }
                ))
                viewModel.onCheckPointCompleted()
            }
        })

        return binding.root
    }

//    private fun init() {
//        activity?.let {
//            ViewModelProviders.of(it).get(MainViewModel::class.java).apply {
//                currentFragmentType.value = CurrentFragmentType.PROFILE
//            }
//        }
//    }
}