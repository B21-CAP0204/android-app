package com.juarai.capstone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.juarai.capstone.R
import com.juarai.capstone.databinding.FragmentHomeBinding
import es.dmoral.toasty.Toasty
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by inject()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser().observe(viewLifecycleOwner, {
            if (it.isEmpty())
                binding.cvData.visibility = View.GONE
            else
                with(binding) {
                    tvName.text = it[0].name
                    tvNik.text = it[0].nik
                    cvData.setOnClickListener {
                        Toasty.success(requireContext(), "Application still processed", Toasty.LENGTH_SHORT).show()
                    }
                }
                hideOther()
        })

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_registerFragment)
        }
    }

    private fun hideOther() {
        with(binding) {
            lottieRegister.visibility = View.GONE
            btnRegister.visibility = View.GONE
        }
    }
}