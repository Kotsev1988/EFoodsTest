package com.example.efoods.presentation.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.efoods.presentation.viewModel.categoryAppSatate.DetailAppState
import com.example.efoods.domain.entity.Dishe
import com.example.efoods.domain.myCard.IMyCardProducts
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val myCard: IMyCardProducts
): ViewModel() {

    private val _lists: MutableLiveData<DetailAppState> = MutableLiveData()
    val listItem: LiveData<DetailAppState>
        get() {
            return _lists
        }

     private lateinit var dishe: Dishe

    fun init(dish: Dishe?){

        if (dish != null) {
            dishe = dish
            _lists.value = DetailAppState.OnSuccess(dish)
            isContain(dish.id)
        }
    }


    @SuppressLint("CheckResult")
    private fun isContain(id: Int) {
        myCard.isContain(id).observeOn(AndroidSchedulers.mainThread()).subscribe({isContain ->
            _lists.value = DetailAppState.IsContain(isContain)

        },
            {
                _lists.value = it.message?.let { error -> DetailAppState.Error(error) }
            })
    }

    fun addToCard() {
        myCard.let {
            myCard.insertProductToMyCard(dishe).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _lists.value = DetailAppState.AddToCard(true)
                }, {
                    _lists.value = it.message?.let { error -> DetailAppState.Error(error) }
                })
        }

    }
}