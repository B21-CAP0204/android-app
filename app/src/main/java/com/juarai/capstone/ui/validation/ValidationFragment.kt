package com.juarai.capstone.ui.validation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.juarai.capstone.databinding.FragmentValidationBinding
import es.dmoral.toasty.Toasty

class ValidationFragment : Fragment() {

    val viewModel: ValidationViewModel by viewModels()

    private var _binding: FragmentValidationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentValidationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lblKK.editText?.setText("123123123123")

        setNavigation()
    }

    private fun setNavigation() {
        val action = ValidationFragmentDirections.actionValidationFragmentToHomeFragment()
        with(binding) {
            btnAcc.setOnClickListener {
                Toasty.success(requireContext(), "Daftar Sukses", Toasty.LENGTH_SHORT).show()
                findNavController().navigate(action)
            }
            btnCancel.setOnClickListener {
                Toasty.error(requireContext(), "Daftar Dibatalkan", Toasty.LENGTH_SHORT).show()
                findNavController().navigate(action)
            }
        }
    }
}