package com.aridev.cordero.twitdeas.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aridev.cordero.twitdeas.R
import com.aridev.cordero.twitdeas.databinding.FragmentListIdeasBinding
import com.aridev.cordero.twitdeas.ui.router.Router
import com.aridev.cordero.twitdeas.ui.view.adapters.IdeasAdapter
import com.aridev.cordero.twitdeas.ui.view.dialog.DialogAddIdea
import com.aridev.cordero.twitdeas.ui.view.dialog.DialogInit
import com.aridev.cordero.twitdeas.ui.view.dialog.ProgressDialog
import com.aridev.cordero.twitdeas.ui.viewModel.ListIdeasViewModel
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import org.koin.android.ext.android.inject


class ListIdeasFragment : Fragment() {
    private var _binding: FragmentListIdeasBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListIdeasViewModel by viewModels()
    private val adapter: IdeasAdapter by inject()

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var translator: Translator
    private lateinit var progress : ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListIdeasBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        initTranslator()
        setListeners()
        setAdapter()
        setObservers()
    }

    private fun setTranslate() {
        progress.show()
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        lifecycle.addObserver(translator)
        translator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                progress.dismiss()
                viewModel.getIdeas()
            }
            .addOnFailureListener { exception ->
                progress.dismiss()
                Toast.makeText(requireContext(),"Asegurese de estar conectado a internet.",Toast.LENGTH_SHORT).show()

            }
    }

    private fun setUi() {

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        progress = ProgressDialog(requireContext())
    }

    private fun setListeners() {
        adapter.callback = { idea->
            DialogAddIdea(requireContext()) {
                idea.comment = it
                viewModel.addIdea(idea)
            }.show()
        }

        binding.swLanguage.setOnCheckedChangeListener { compoundButton, isChecked ->
            viewModel.changeLanguage(isChecked)
        }

        binding.rvIdeas.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    layoutManager.apply {
                        if (childCount + findFirstVisibleItemPosition() >= itemCount / 2) {
                            viewModel.getIdeas()
                        }
                    }
                }
            }
        })
    }

    private fun initTranslator() {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()
        translator = Translation.getClient(options)
    }

    private fun setAdapter() {
        adapter.list = viewModel.listIdeas.value ?: arrayListOf()
        adapter.translator = translator
        binding.rvIdeas.layoutManager = layoutManager
        binding.rvIdeas.adapter = adapter
    }

    private fun setObservers() {
        viewModel.apply {
            listIdeas.observe(viewLifecycleOwner) {
                adapter.list = it
            }

            errorApp.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }

            isLoading.observe(viewLifecycleOwner) {
                progress.show()
            }

            cancelLoading.observe(viewLifecycleOwner) {
                progress.dismiss()
            }

            changeLanguageEn.observe(viewLifecycleOwner) {
                viewModel.getIdeas()
            }

            changeLanguageEs.observe(viewLifecycleOwner) {
                Toast.makeText(
                    requireContext(),
                    "El sistema de traducción puede demorar un momento.",
                    Toast.LENGTH_LONG
                ).show()
                setTranslate()
                //Router.replace(ListIdeasFragment(),requireActivity().supportFragmentManager, R.id.container_fragments)
            }

            isEs.observe(viewLifecycleOwner) {
                setTranslate()
            }

            init()
        }
    }
}