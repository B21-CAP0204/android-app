package com.juarai.capstone.ui.validation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.juarai.capstone.data.local.UserEntity
import com.juarai.capstone.databinding.FragmentValidationBinding
import es.dmoral.toasty.Toasty
import org.koin.android.ext.android.inject

class ValidationFragment : Fragment() {

    val viewModel: ValidationViewModel by inject()

    private var _binding: FragmentValidationBinding? = null
    private val binding get() = _binding!!

    private var user: UserEntity? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentValidationBinding.inflate(inflater, container, false)
        user = arguments?.let { ValidationFragmentArgs.fromBundle(it).user }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()

        setNavigation()
    }

    private fun setData() {
        with(binding) {
            lblKK.editText?.setText("3322172908083329")
            lblNIK.editText?.setText(user?.nik.toString())
            lblName.editText?.setText(user?.name)
            lblbirth.editText?.setText("${user?.birthDate} / ${user?.birthPlace}")
            lblGolDar.editText?.setText(user?.golDar)
            lblGender.editText?.setText(user?.gender)
            lblAddress.editText?.setText(user?.address)
            lblrtrw.editText?.setText("${user?.rt} / ${user?.rw}")
            lblkel.editText?.setText(user?.kelurahan)
            lblkec.editText?.setText(user?.kecamatan)
            lblReligion.editText?.setText(user?.religion)
            lbloccupation.editText?.setText(user?.occupation)
        }
    }

    private fun setNavigation() {
        val action = ValidationFragmentDirections
        with(binding) {
            btnAcc.setOnClickListener {
                viewModel.insert(user!!)
                Toasty.success(requireContext(), "Daftar Sukses", Toasty.LENGTH_SHORT).show()
                findNavController().navigate(action.actionValidationFragmentToHomeFragment())
            }
            btnCancel.setOnClickListener {
                Toasty.error(requireContext(), "Daftar Dibatalkan", Toasty.LENGTH_SHORT).show()
                findNavController().navigate(action.actionValidationFragmentToRegisterFragment())
            }
        }
    }
}