package com.mgabor.coroutine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mgabor.coroutine.R
import com.mgabor.coroutine.databinding.FragmentStateFlowExampleBinding
import com.mgabor.coroutine.util.fragmentScope
import com.mgabor.coroutine.viewmodel.StateFlowExampleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_state_flow_example.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class StateFlowExampleFragment : Fragment() {

    private val viewModel: StateFlowExampleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentStateFlowExampleBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_state_flow_example, container, false
        )

        binding.vm = viewModel


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCountObserver()
    }

    private fun initCountObserver() {
        fragmentScope.launchWhenResumed {
            viewModel.countState.collect {
                withContext(Dispatchers.Main) {
                    countText?.text = "$it"
                }
            }
        }

        fragmentScope.launchWhenResumed {
            viewModel.countSharedFlow.collect {
                withContext(Dispatchers.Main) {
                    countText?.text = "$it"
                }
            }
        }

        fragmentScope.launchWhenResumed {
            delay(5000)
            viewModel.countSharedFlow.collect {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "My value: $it", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}