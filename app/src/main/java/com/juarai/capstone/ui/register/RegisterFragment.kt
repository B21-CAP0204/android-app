package com.juarai.capstone.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.juarai.capstone.R
import com.juarai.capstone.databinding.RegisterFragmentBinding
import org.koin.android.ext.android.inject

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by inject()
    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemGoldar = listOf("A", "B", "AB", "O")
        val adapterGoldar = context?.let { ArrayAdapter(it, R.layout.list_goldar, itemGoldar) }
        (binding.spinnerGoldar.editText as? AutoCompleteTextView)?.setAdapter(adapterGoldar)
        val itemAgama = listOf("Islam", "Kristen", "Katholik", "Hindu", "Budha", "Konghucu")
        val adapterAgama = context?.let { ArrayAdapter(it, R.layout.list_goldar, itemAgama) }
        (binding.spinnerAgama.editText as? AutoCompleteTextView)?.setAdapter(adapterAgama)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_cameraFragment)
        }
    }
}