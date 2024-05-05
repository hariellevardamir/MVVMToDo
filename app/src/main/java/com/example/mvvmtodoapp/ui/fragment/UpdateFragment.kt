package com.example.mvvmtodoapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.mvvmtodoapp.R
import com.example.mvvmtodoapp.databinding.FragmentUpdateBinding
import com.example.mvvmtodoapp.ui.viewModel.MainViewModel
import com.example.mvvmtodoapp.ui.viewModel.UpdateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: UpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        // getBundleData
        val bundle: UpdateFragmentArgs by navArgs()
        val toDo = bundle.todo
        binding.editTextName.setText(toDo.name)

        binding.btnUpdate.setOnClickListener {
            val newName = binding.editTextName.text.toString()
            viewModel.update(toDo.id, newName)
            Toast.makeText(requireContext(), "$newName updated", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: UpdateViewModel by viewModels()
        viewModel = tempViewModel
    }
}