package com.juarai.capstone.ui.camera

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.storage.FirebaseStorage
import com.juarai.capstone.data.network.UserResponse
import com.juarai.capstone.databinding.FragmentCameraBinding
import com.juarai.capstone.util.DataMapper

class CameraFragment : Fragment() {

    val viewModel: CameraViewModel by viewModels()

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private var fileUri: Uri? = null
    private var user: UserResponse? = null
    private val storageRef = FirebaseStorage.getInstance("gs://juarai_bucket/").reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        user = arguments?.let { CameraFragmentArgs.fromBundle(it).user }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivSelfie.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .maxResultSize(1920, 1080)
                .cameraOnly()
                .createIntent { intent ->
                    startPhotoResult.launch(intent)
                }
        }
        binding.btnUploadKK.setOnClickListener {
            Log.d("fileURI", fileUri.toString())
            val imgRef = storageRef.child("kk/${user?.nik}")
            val uploadTask = fileUri?.let { it1 -> imgRef.putFile(it1) }
            uploadTask?.addOnFailureListener{
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                Log.e("uploadStorage", it.stackTraceToString())
            }
            uploadTask?.addOnSuccessListener {
                Toast.makeText(context, "Upload success!", Toast.LENGTH_LONG).show()
            }
            viewModel.addUsertoFirestore(user!!)
            val validationData = DataMapper.toValidation(user!!, fileUri.toString())
            val action = CameraFragmentDirections.actionCameraFragmentToValidationFragment(validationData)
            findNavController().navigate(action)
        }
    }

    private val startPhotoResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    fileUri = data?.data!!
                    Glide.with(this)
                        .load(fileUri)
                        .into(binding.ivSelfie)
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Dibatalkan", Toast.LENGTH_SHORT).show()
                }
            }
        }
}