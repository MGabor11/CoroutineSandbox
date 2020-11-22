package com.mgabor.coroutine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mgabor.coroutine.R
import com.mgabor.coroutine.databinding.FragmentSuspendFunBinding
import com.mgabor.coroutine.ui.adapter.BeersAdapter
import com.mgabor.coroutine.util.fragmentScope
import com.mgabor.coroutine.util.launchOnDefault
import com.mgabor.coroutine.viewmodel.SuspendFunViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_state_flow_example.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SuspendFunFragment : Fragment() {

    private val viewModel: SuspendFunViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSuspendFunBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_suspend_fun, container, false
        )

        binding.vm = viewModel
        binding.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.lifecycleOwner = this
        binding.listAdapter = BeersAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCountObserver()
    }

    private fun initCountObserver() {
        fragmentScope.launchOnDefault {
            viewModel.infoState
                .filter { it.isNotEmpty() }
                .collect {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}