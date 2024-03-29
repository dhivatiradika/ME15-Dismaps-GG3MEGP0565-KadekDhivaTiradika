package com.bydhiva.dismaps.ui.error

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bydhiva.dismaps.R
import com.bydhiva.dismaps.base.activityViewModelBuilder
import com.bydhiva.dismaps.databinding.FragmentErrorPlaceholderBinding
import com.bydhiva.dismaps.ui.main.MainViewModel
import com.bydhiva.dismaps.utils.changeDrawable

class ErrorPlaceholderFragment : Fragment() {
    private var _binding: FragmentErrorPlaceholderBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModelBuilder<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorPlaceholderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            viewModel.getReports()
        }
        viewModel.mainUIEvent.observe(viewLifecycleOwner) {
            when (it) {
                is MainViewModel.MainUIEvent.ErrorEvent -> {
                    setPlaceholder(it.message, R.drawable.ph_error_screen)
                }
                is MainViewModel.MainUIEvent.EmptyEvent -> {
                    setPlaceholder(resources.getString(R.string.empty_message), R.drawable.ph_empty_screen)
                }
                else -> {}
            }
        }
    }

    private fun setPlaceholder(text: String, resId: Int) = with(binding) {
        tvErrorMessage.text = text
        imageView.changeDrawable(resId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}