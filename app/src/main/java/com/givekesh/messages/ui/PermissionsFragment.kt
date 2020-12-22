package com.givekesh.messages.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.givekesh.messages.R
import com.givekesh.messages.databinding.FragmentPermissionsBinding
import com.givekesh.messages.util.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PermissionsFragment : Fragment() {

    private var _binding: FragmentPermissionsBinding? = null
    private val binding get() = _binding!!

    private var _requestPermissionLauncher:
            ActivityResultLauncher<Array<String>>? = null
    private val requestPermissionLauncher get() = _requestPermissionLauncher!!

    private var _activityLauncher:
            ActivityResultLauncher<Intent>? = null
    private val activityLauncher get() = _activityLauncher!!

    private lateinit var utils: Utils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPermissionsBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils = Utils(requireContext())
        if (utils.isPermissionsGranted()
        ) {
            navigateToThreads()
        } else {
            setupListeners()
            _requestPermissionLauncher = getPermissionLauncher()
            _activityLauncher = getSettingsLauncher()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _requestPermissionLauncher?.unregister()
        _activityLauncher?.unregister()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    private fun setupListeners() {
        binding.apply {
            rationalExit.setOnClickListener { requireActivity().finish() }
            rationalActionSettings.setOnClickListener {
                val intent = utils.getSettingsIntent()
                activityLauncher.launch(intent)
            }
            rationalActionNext.setOnClickListener { requestPermission() }
        }
    }

    private fun getPermissionLauncher() =
        requireActivity().registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { results: Map<String, Boolean> ->
            var isGranted = true
            for (result in results) {
                if (!result.value) {
                    isGranted = false
                    if (!shouldShowRequestPermissionRationale(
                            result.key
                        )
                    ) {
                        binding.apply {
                            rationalTitle.text =
                                getString(R.string.permission_denied_title)
                            rationalContent.text =
                                getString(R.string.permission_denied_content)
                            rationalActionNext.visibility = View.GONE
                            rationalActionSettings.visibility = View.VISIBLE
                        }
                        break
                    }
                }
            }
            if (isGranted)
                navigateToThreads()
        }

    private fun getSettingsLauncher() =
        requireActivity().registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (utils.isPermissionsGranted())
                navigateToThreads()
        }

    private fun requestPermission() {
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_CONTACTS
            )
        )
    }

    private fun navigateToThreads() {
        findNavController().navigate(
            R.id.action_PermissionsFragment_to_ThreadsFragment
        )
    }
}
