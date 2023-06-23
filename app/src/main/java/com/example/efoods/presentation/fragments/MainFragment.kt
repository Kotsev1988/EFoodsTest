package com.example.efoods.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.efoods.App
import com.example.efoods.R
import com.example.efoods.databinding.FragmentMainBinding
import com.example.efoods.presentation.viewModel.MainViewModel
import com.example.efoods.presentation.viewModel.mainAppState.AppState
import javax.inject.Inject


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var  mainAdapter: com.example.efoods.presentation.adapters.MainAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent
            .inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val bundleBaseFrag = Bundle()
        bundleBaseFrag.putString("headerText", "location")
        parentFragment?.setFragmentResult(KAY_PARENT, bundleBaseFrag)
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.clickEvent.observe(viewLifecycleOwner){
            val name = it.goToMenuDetailsIfNotHandled()
            val bundle = Bundle()

            if (name != null) {
                bundle.putString("PRODUCT_ID", name)


                val bundleBaseFrag = Bundle()
                bundleBaseFrag.putString("headerText", name)
                findNavController().navigate(R.id.action_mainFragment_to_categoryFragment, bundleBaseFrag)

            }
        }

        viewModel.listItem.observe(viewLifecycleOwner){
            renderData(it)
        }
        viewModel.init()
        binding.frameLoad.visibility = View.VISIBLE
    }

    private fun renderData(it: AppState) {

        when(it){
            is AppState.OnSuccess ->{
                mainAdapter = com.example.efoods.presentation.adapters.MainAdapter(it.productsListPresenter)
                    .apply {
                    App.instance.appComponent.inject(this)
                }
                binding.recyclerView.adapter = mainAdapter
                binding.frameLoad.visibility = View.GONE
            }
            is AppState.Error ->{
                Toast.makeText(requireActivity(), it.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

}