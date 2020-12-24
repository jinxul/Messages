package com.givekesh.messages.ui.threads

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.givekesh.messages.databinding.FragmentThreadsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThreadsFragment : Fragment() {

    private val viewModel: ThreadsVm by viewModels()
    private var _binding: FragmentThreadsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreadsBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ThreadsAdapter()
        binding.list.adapter = adapter
        viewModel.messages.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}