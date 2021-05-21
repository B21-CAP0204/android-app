package com.juarai.capstone.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juarai.capstone.R
import com.juarai.capstone.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel
    private var _binding: RegisterFragmentBinding? = null
    private val binding = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}