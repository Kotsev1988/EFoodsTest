package com.example.efoods.presentation.fragments

import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.efoods.App
import com.example.efoods.R
import com.example.efoods.databinding.FragmentDetailBinding
import com.example.efoods.presentation.viewModel.DetailViewModel
import com.example.efoods.presentation.viewModel.categoryAppSatate.DetailAppState
import javax.inject.Inject

class DetailFragment : DialogFragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        App.instance.appComponent
            .inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun getTheme(): Int  = R.style.RoundedDialogStyle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      val  dish = arguments?.let{
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
              it.getParcelable(KAY_PARENT_DIALOG, com.example.efoods.domain.entity.Dishe::class.java)
          }else{
              it.getParcelable(KAY_PARENT_DIALOG)
          }

      }

        viewModel.listItem.observe(viewLifecycleOwner){
            renderData(it)
        }

        if (dish != null) {
            viewModel.init(dish)
        }


        binding.addToMyCard.setOnClickListener {
            viewModel.addToCard()
        }

        binding.close.setOnClickListener {
            this.dismiss()
        }

    }

    private fun renderData(it: DetailAppState) {
        when(it){
            is DetailAppState.OnSuccess ->{

                binding.dishName.text= it.dishe.name
                binding.dishPrice.text = it.dishe.price.toString() +" Р "

                val string  = SpannableString(it.dishe.weight.toString() + " г")
                string.setSpan(BulletSpan(10), 0, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

                binding.dishWeight.text = string
                binding.dishDescription.text = it.dishe.description

                Glide.with(binding.dishImage.context)
                    .load(it.dishe.image_url)
                    .centerInside()
                    .into(binding.dishImage)
            }

            is DetailAppState.IsContain ->{

                if (it.isContain) {

                    binding.addToMyCard.visibility = View.GONE
                    binding.existInMyCard.visibility = View.VISIBLE
                }else{
                    binding.addToMyCard.visibility = View.VISIBLE
                    binding.existInMyCard.visibility = View.GONE
                }

            }

            is DetailAppState.AddToCard ->{

                binding.addToMyCard.visibility = View.GONE
                binding.existInMyCard.visibility = View.VISIBLE

            }

            is DetailAppState.Error ->{
                Toast.makeText(requireActivity(), it.error, Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(dishe: com.example.efoods.domain.entity.Dishe) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KAY_PARENT_DIALOG, dishe)
                }
            }
    }
}