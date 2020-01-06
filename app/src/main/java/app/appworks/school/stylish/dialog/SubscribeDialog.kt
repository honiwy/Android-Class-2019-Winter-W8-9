package app.appworks.school.stylish.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import app.appworks.school.stylish.R
import app.appworks.school.stylish.databinding.DialogSubsribeBinding

class SubscribeDialog : AppCompatDialogFragment() {

    private val viewModel: SubscribeViewModel by lazy {
        ViewModelProviders.of(this).get(SubscribeViewModel::class.java)
    }//要用到的時候再創建才不會浪費記憶體資源

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