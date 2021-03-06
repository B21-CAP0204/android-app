package com.juarai.capstone.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.juarai.capstone.R
import com.juarai.capstone.data.network.UserResponse
import com.juarai.capstone.databinding.RegisterFragmentBinding
import org.koin.android.ext.android.inject


class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by inject()
    private var _binding: RegisterFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // GolDar
        val itemGoldar = listOf("A", "B", "AB", "O")
        val adapterGoldar = context?.let { ArrayAdapter(it, R.layout.list_goldar, itemGoldar) }
        (binding.spinnerGoldar.editText as? AutoCompleteTextView)?.setAdapter(adapterGoldar)

        // Agama
        val itemAgama = listOf("Islam", "Kristen", "Katholik", "Hindu", "Budha", "Konghucu")
        val adapterAgama = context?.let { ArrayAdapter(it, R.layout.list_goldar, itemAgama) }
        (binding.spinnerAgama.editText as? AutoCompleteTextView)?.setAdapter(adapterAgama)

        // Bundle to Object
        binding.btnNext.setOnClickListener {
            val radioButtonGroup = binding.genderGroup
            val radioButtonID = radioButtonGroup.checkedRadioButtonId
            val radioButton: View = radioButtonGroup.findViewById(radioButtonID)
            val idx: Int = radioButtonGroup.indexOfChild(radioButton)
            val r = radioButtonGroup.getChildAt(idx) as RadioButton
            val selectedtext = r.text.toString()
            val UserResponse = UserResponse(
                nik = binding.editNIK.editText?.text.toString(),
                name = binding.editName.editText?.text.toString(),
                birthPlace = binding.editBirthplace.editText?.text.toString(),
                birthDate = binding.editBirthdate.editText?.text.toString(),
                golDar = binding.spinnerGoldar.editText?.text.toString(),
                gender = selectedtext,
                address = binding.editAddress.editText?.text.toString(),
                rt = Integer.parseInt(binding.editRT.editText?.text.toString()),
                rw = Integer.parseInt(binding.editRW.editText?.text.toString()),
                kelurahan = binding.editKelurahan.editText?.text.toString(),
                kecamatan = binding.editKecamatan.editText?.text.toString(),
                religion = binding.spinnerAgama.editText?.text.toString(),
                occupation = binding.editPekerjaan.editText?.text.toString()
            )
            val action: NavDirections =
                RegisterFragmentDirections.actionRegisterFragmentToCameraFragment(UserResponse)
            findNavController().navigate(action)
        }
    }
}