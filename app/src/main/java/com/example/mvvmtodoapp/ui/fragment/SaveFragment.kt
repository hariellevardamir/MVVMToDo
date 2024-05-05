package com.example.mvvmtodoapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.mvvmtodoapp.R
import com.example.mvvmtodoapp.data.entity.ToDos
import com.example.mvvmtodoapp.databinding.FragmentSaveBinding
import com.example.mvvmtodoapp.ui.viewModel.MainViewModel
import com.example.mvvmtodoapp.ui.viewModel.SaveViewModel
import com.example.mvvmtodoapp.utils.transition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaveFragment : Fragment() {

    private lateinit var binding: FragmentSaveBinding
    private lateinit var viewModel: SaveViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSaveBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        clickedSaveButton()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: SaveViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun clickedSaveButton() {
        binding.btnSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            viewModel.save(name)
            Toast.makeText(requireContext(), "$name saved", Toast.LENGTH_SHORT).show()
        }
    }
}
