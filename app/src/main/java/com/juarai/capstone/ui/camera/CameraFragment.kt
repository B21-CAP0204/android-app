package com.juarai.capstone.ui.camera

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.storage.FirebaseStorage
import com.juarai.capstone.R
import com.juarai.capstone.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding
    var fileUri: Uri? = null
    val storageRef = FirebaseStorage.getInstance("gs://neat-coast-314213.appspot.com/").reference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
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
            val imgRef = storageRef.child("kk/${fileUri?.lastPathSegment}")
            val uploadTask = fileUri?.let { it1 -> imgRef.putFile(it1) }
            uploadTask?.addOnFailureListener{
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                Log.e("uploadStorage", it.stackTraceToString())
            }
            uploadTask?.addOnSuccessListener {
                Toast.makeText(context, "Upload success!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val startPhotoResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    fileUri = data?.data!!
                    println(fileUri)
                    binding.ivSelfie.setImageURI(fileUri)
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