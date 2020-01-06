package app.appworks.school.stylish.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import app.appworks.school.stylish.R
import app.appworks.school.stylish.collect.CollectViewModel
import app.appworks.school.stylish.databinding.DialogSubsribeBinding
import app.appworks.school.stylish.ext.getVmFactory

class SubscribeDialog : AppCompatDialogFragment() {

    private val viewModel by viewModels<SubscribeViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val binding = DialogSubsribeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.dialog = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MessageDialog)
    }
}