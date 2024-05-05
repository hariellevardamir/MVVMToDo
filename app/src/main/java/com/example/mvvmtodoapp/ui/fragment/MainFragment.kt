package com.example.mvvmtodoapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmtodoapp.R
import com.example.mvvmtodoapp.data.entity.ToDos
import com.example.mvvmtodoapp.databinding.FragmentMainBinding
import com.example.mvvmtodoapp.ui.adapter.ToDosAdapter
import com.example.mvvmtodoapp.utils.transition
import com.example.mvvmtodoapp.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        clickedFabButton()
        setupAdapter()
        searchToDos()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun setupAdapter() {

        viewModel.toDosListLiveData.observe(viewLifecycleOwner) { observeAllToDos ->
            val toDosAdapter = ToDosAdapter(requireContext(), observeAllToDos, viewModel)
            binding.toDosRecyclerView.adapter = toDosAdapter
        }
        binding.toDosRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun clickedFabButton() {
        binding.fab.setOnClickListener {
            //  Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_saveFragment)
            Navigation.transition(it, R.id.action_mainFragment_to_saveFragment)
        }
    }

    private fun searchToDos() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchToDos(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchToDos(query)
                return true
            }
        })
    }

    // main fragmenta dönüldüğü zaman bunun farkına varmamızı sağlar. Ve bu sayede listemizi güncelleyebiliriz.
    override fun onResume() {
        super.onResume()
        viewModel.loadAllToDos()
    }

}