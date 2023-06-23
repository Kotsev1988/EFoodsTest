package com.example.efoods.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.efoods.App
import com.example.efoods.R
import com.example.efoods.databinding.FragmentCategoryBinding
import com.example.efoods.presentation.adapters.BannersHorizontalAdapter
import com.example.efoods.presentation.adapters.DishesGridAdapter
import com.example.efoods.presentation.adapters.CategoryHorizontalAdapter
import com.example.efoods.presentation.viewModel.CategoryViewModel
import com.example.efoods.presentation.viewModel.categoryAppSatate.CategoryAppState
import javax.inject.Inject

const val KAY_PARENT_DIALOG = "key_parent_dialog"

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!



    private var dishesGridAdapter: DishesGridAdapter? = null

    private var categoryHorizontalAdapter: CategoryHorizontalAdapter? = null

    private var bannerHorizontalAdapter: BannersHorizontalAdapter = BannersHorizontalAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CategoryViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CategoryViewModel::class.java]
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

        val id = arguments?.getString("metadataFileSyncFilter")
        val bundleBaseFrag = Bundle()
        bundleBaseFrag.putString("headerText", id)


        _binding = FragmentCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init()

        viewModel.listItem.observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.frameLoadDishes.visibility = View.VISIBLE

    }

    private fun renderData(it: CategoryAppState) {

        when (it) {
            is CategoryAppState.OnSuccess ->{
                val menu = com.example.efoods.domain.entity.Menus(arrayListOf())
                menu.dishes.addAll(it.dishes)




                binding.startHorizontalBanner.adapter = bannerHorizontalAdapter
                val stringBanner = arrayListOf<String>()
                stringBanner.add("One")
                stringBanner.add("Two")
                stringBanner.add("three")
                bannerHorizontalAdapter.setData(stringBanner)

                dishesGridAdapter = DishesGridAdapter(it.listDishes)
                binding.dishesRecycle.adapter = dishesGridAdapter

                categoryHorizontalAdapter = CategoryHorizontalAdapter(it.listMenu)
                binding.startHorizontal.adapter = categoryHorizontalAdapter
                binding.frameLoadDishes.visibility = View.GONE
            }

            is CategoryAppState.SetDishes ->{
                val menu = com.example.efoods.domain.entity.Menus(arrayListOf())
                menu.dishes.addAll(it.dishes)

                dishesGridAdapter?.setData(it.listDishes)

            }

            is CategoryAppState.Error ->{
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }

            is CategoryAppState.ShowDialogFragment ->{
                showFragmentDialog(it.dish)
            }
        }
    }

    private lateinit var fragmentDialog: DetailFragment

    private fun showFragmentDialog(dish: com.example.efoods.domain.entity.Dishe) {
        fragmentDialog = DetailFragment.newInstance(dish)
        fragmentDialog.show(this.childFragmentManager, "tag")

    }



}