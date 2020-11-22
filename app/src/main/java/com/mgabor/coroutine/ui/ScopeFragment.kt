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
import com.mgabor.coroutine.databinding.FragmentScopeBinding
import com.mgabor.coroutine.ui.adapter.BeersAdapter
import com.mgabor.coroutine.viewmodel.BasicExampleViewModel
import com.mgabor.coroutine.viewmodel.ScopeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScopeFragment : Fragment() {

    private val viewModel: ScopeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentScopeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_scope, container, false
        )

        binding.vm = viewModel

        return binding.root
    }
}