package com.juarai.capstone.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.juarai.capstone.R
import com.juarai.capstone.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var _binding: RegisterFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        val itemGoldar = listOf("A", "B", "AB", "O")
        val adapterGoldar = context?.let { ArrayAdapter(it, R.layout.list_goldar, itemGoldar) }
        (_binding.spinnerGoldar.editText as? AutoCompleteTextView)?.setAdapter(adapterGoldar)
        val itemAgama = listOf("Islam", "Kristen", "Katholik", "Hindu", "Budha", "Konghucu")
        val adapterAgama = context?.let { ArrayAdapter(it, R.layout.list_goldar, itemAgama) }
        (_binding.spinnerAgama.editText as? AutoCompleteTextView)?.setAdapter(adapterAgama)
        return _binding.root
    }
}