package com.aridev.cordero.twitdeas.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.aridev.cordero.twitdeas.R
import com.aridev.cordero.twitdeas.databinding.FragmentMyIdeasBinding
import com.aridev.cordero.twitdeas.ui.view.adapters.MyIdeasAdapter
import com.aridev.cordero.twitdeas.ui.viewModel.MyIdeasViewModel
import org.koin.android.ext.android.inject

class MyIdeasFragment : Fragment() {
    private var _binding: FragmentMyIdeasBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MyIdeasViewModel by viewModels()
    private val adapter : MyIdeasAdapter by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentMyIdeasBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setUi()
            setListeners()
            setAdapter()
            setObservers()
    }

    private fun setUi() {

    }

    private fun setListeners() {

    }

    private fun setAdapter() {
        adapter.list = viewModel.listIdeas.value ?: arrayListOf()
        binding.rvMyIdeas.adapter = adapter
    }

    private fun setObservers() {
        viewModel.apply {

            listIdeas.observe(viewLifecycleOwner) {
                adapter.list = it
            }

            getIdeas()
        }
    }
}