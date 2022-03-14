package com.example.breweryapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breweryapp.databinding.BreweriesFragmentBinding
import com.example.breweryapp.presentation.BreweryViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.breweryapp.utils.Resource.*

@AndroidEntryPoint
class BreweriesFragment : Fragment() {

    private val viewModel: BreweryViewModel by viewModels()

    private var _binding: BreweriesFragmentBinding? = null
    private val binding get() = _binding!!
    private var toast: Toast? = null

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
            when (it) {
                is Loading -> {
                    showLoadingBar(true)
                }
                is Success -> {
                    it.data?.let { data ->
                        breweryAdapter.submitList(data)
                        if (data.isEmpty()) {
                            displayEmptyMessage(true)
                        } else {
                            displayEmptyMessage(false)
                        }
                    }
                    showLoadingBar(false)
                }
                is Error -> {
                    showLoadingBar(false)
                }
            }

        }
    }

    private fun displayEmptyMessage(show: Boolean) {
        if (show) {
            binding.emptyTv.visibility = View.VISIBLE
        } else {
            binding.emptyTv.visibility = View.GONE
        }
    }

    private fun showLoadingBar(show: Boolean) {
        if (show) {
            binding.pBar.visibility = View.VISIBLE
        } else {
            binding.pBar.visibility = View.GONE
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