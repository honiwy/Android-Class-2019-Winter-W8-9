package app.appworks.school.stylish.comment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import app.appworks.school.stylish.NavigationDirections
import app.appworks.school.stylish.R
import app.appworks.school.stylish.databinding.FragmentCommentBinding
import app.appworks.school.stylish.databinding.FragmentDetailBinding
import app.appworks.school.stylish.detail.*
import app.appworks.school.stylish.ext.getVmFactory
import app.appworks.school.stylish.util.CurrentFragmentType

class CommentFragment : Fragment() {

    private val viewModel by viewModels<CommentViewModel> {
        getVmFactory(
            CommentFragmentArgs.fromBundle(
                arguments!!
            ).productKey
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCommentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            viewModel.star.value = fl.toInt()
        }

        viewModel.leaveComment.observe(this, Observer {
            it?.let {
                if (it) findNavController().popBackStack()
            }
        })

        return binding.root
    }


}