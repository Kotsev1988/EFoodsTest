package com.example.efoods.presentation.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.efoods.R
import com.example.efoods.databinding.FragmentBaseBinding
import com.example.efoods.presentation.viewModel.BaseViewModel
import com.example.efoods.presentation.viewModel.appStateLocation.AppStateLocation
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Date


const val REQUEST_CODE = 30

class BaseFragment : Fragment() {

    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!

    var city = ""


    private val viewModel: BaseViewModel by lazy {
        ViewModelProvider(this)[BaseViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentBaseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView =
            view.findViewById<BottomNavigationView>(R.id.mainBottomNavigationView)

        val navController = (childFragmentManager.findFragmentById(R.id.mainContainerView)
                as NavHostFragment).navController

        bottomNavigationView.setupWithNavController(navController)

        viewModel.liveDataLocation.observe(viewLifecycleOwner) {
            renderData(it)
        }

        checkPermission()

    }

    private fun renderData(it: AppStateLocation) {

        when (it) {
            is AppStateLocation.Success -> {

                city = it.cities

                val  spinner: ArrayList<String> = arrayListOf()
                spinner.add(it.cities)

                binding.location.adapter = ArrayAdapter(binding.location.context, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, spinner)
            }

            is AppStateLocation.EmptyData -> {
                context?.let { context ->
                    showDialog(
                        context.getString(R.string.dialog_title_gps_turnoff),
                        context.getString(R.string.dialog_message_last_location_unknown)
                    )
                }
            }

            is AppStateLocation.ShowRationalDialog -> {
                showRationaleDialog()
            }

            is AppStateLocation.Error -> {

            }
        }

    }

    private fun checkPermission() {
        requireActivity().let {
            when {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED -> {
                    viewModel.getLocation()
                }

                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                    showRationaleDialog()
                }

                else -> {
                    requestPermission()
                }
            }
        }
    }

    private fun requestPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                var grantedPermission = 0;
                if (grantResults.isNotEmpty()) {
                    for (i in grantResults) {
                        if (i == PackageManager.PERMISSION_GRANTED) {
                            grantedPermission++
                        }
                    }
                    if (grantResults.size == grantedPermission) {
                        viewModel.getLocation()
                    } else {
                        showDialog(
                            getString(R.string.dialog_title_no_gps),
                            getString(R.string.dialog_message_no_gps)
                        )
                    }
                } else {
                    showDialog(
                        getString(R.string.dialog_title_no_gps),
                        getString(R.string.dialog_message_no_gps)
                    )
                }
            }
        }
    }

    private fun showDialog(tile: String, message: String) {

        requireContext().let {
            AlertDialog.Builder(it)
                .setTitle(tile)
                .setMessage(message)
                .setNegativeButton(R.string.dialog_button_close) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun showRationaleDialog() {
        requireActivity().let {
            AlertDialog.Builder(it)
                .setTitle(R.string.dialog_rationale_title)
                .setMessage(R.string.dialog_rationale_meaasge)
                .setPositiveButton(it.getString(R.string.dialog_rationale_give_access))
                { _, _ ->
                    requestPermission()
                }
                .setNegativeButton(R.string.dialog_rationale_decline) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}