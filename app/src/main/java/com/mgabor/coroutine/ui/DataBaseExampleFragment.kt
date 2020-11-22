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
import com.mgabor.coroutine.databinding.FragmentDatabaseExampleBinding
import com.mgabor.coroutine.ui.adapter.BeersSecondaryAdapter
import com.mgabor.coroutine.viewmodel.BasicExampleViewModel
import com.mgabor.coroutine.viewmodel.DataBaseExampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataBaseExampleFragment : Fragment() {

    private val viewModel: DataBaseExampleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDatabaseExampleBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_database_example, container, false
        )

        binding.vm = viewModel
        binding.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.lifecycleOwner = this
        binding.listAdapter = BeersSecondaryAdapter()

        return binding.root
    }
}
