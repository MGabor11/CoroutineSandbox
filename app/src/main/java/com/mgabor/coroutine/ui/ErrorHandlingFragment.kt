package com.mgabor.coroutine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mgabor.coroutine.R
import com.mgabor.coroutine.databinding.FragmentBasicExampleBinding
import com.mgabor.coroutine.databinding.FragmentErrorHandlingBinding
import com.mgabor.coroutine.viewmodel.BasicExampleViewModel
import com.mgabor.coroutine.viewmodel.ErrorHandlingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorHandlingFragment : Fragment() {

    private val viewModel: ErrorHandlingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentErrorHandlingBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_error_handling, container, false
        )

        binding.vm = viewModel

        return binding.root
    }
}
