package com.example.breweryapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breweryapp.databinding.BreweriesFragmentBinding
import com.example.breweryapp.presentation.BreweryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class BreweriesFragment : Fragment() {

    private val viewModel: BreweryViewModel by viewModels()

    private var _binding: BreweriesFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var breweryAdapter: BreweryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BreweriesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        setupTextListener()

        viewModel.breweries.observe(viewLifecycleOwner) {
            breweryAdapter.submitList(it.data)
        }
    }

    private fun setupTextListener() {
        binding.etSearchBar.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    viewModel.searchQuery.value = s.toString().trim()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        breweryAdapter = BreweryAdapter()
        binding.rvBreweries.apply {
            adapter = breweryAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}