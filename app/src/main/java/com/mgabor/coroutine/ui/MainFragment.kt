package com.mgabor.coroutine.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mgabor.coroutine.R
import com.mgabor.coroutine.databinding.FragmentMainBinding
import com.mgabor.coroutine.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.toBasicExampleButton).setOnClickListener {
            findNavController().navigate(R.id.navDirectionBasicExample)
        }

        view.findViewById<Button>(R.id.toDatabaseExample).setOnClickListener {
            findNavController().navigate(R.id.navDirectionDataBaseExample)
        }

        view.findViewById<Button>(R.id.toStateFlow).setOnClickListener {
            findNavController().navigate(R.id.navDirectionStateFlow)
        }

        view.findViewById<Button>(R.id.toSuspendFun).setOnClickListener {
            findNavController().navigate(R.id.navDirectionSuspendFun)
        }

        view.findViewById<Button>(R.id.toErrorHandling).setOnClickListener {
            findNavController().navigate(R.id.navDirectionErrorHandling)
        }

        view.findViewById<Button>(R.id.toScopePage).setOnClickListener {
            findNavController().navigate(R.id.navDirectionScope)
        }
    }
}
