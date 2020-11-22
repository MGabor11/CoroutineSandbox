package com.mgabor.coroutine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mgabor.coroutine.R
import com.mgabor.coroutine.databinding.FragmentBasicExampleBinding
import com.mgabor.coroutine.ui.adapter.BeersAdapter
import com.mgabor.coroutine.viewmodel.BasicExampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowExampleFragment : Fragment() {

    private val viewModel: BasicExampleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBasicExampleBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_basic_example, container, false
        )

        binding.vm = viewModel
        binding.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.lifecycleOwner = this
        binding.listAdapter = BeersAdapter()

        return binding.root
    }
}