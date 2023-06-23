package com.example.efoods.presentation.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.efoods.domain.myCard.IMyCardProducts
import com.example.efoods.presentation.adapters.myCardItemsView.list.MyCardDishesList
import com.example.efoods.presentation.viewModel.myCardAppState.AppStateMyCard
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MyCardViewModel @Inject constructor(
    private val myCard: IMyCardProducts,
) : ViewModel() {

    private val _lists: MutableLiveData<AppStateMyCard> = MutableLiveData()
    val listItem: LiveData<AppStateMyCard>
        get() {
            return _lists
        }

    private val myCardDishes = MyCardDishesList()
    private val prices: HashMap<Int, Int> = hashMapOf()

    @SuppressLint("CheckResult")
    fun init() {

        myCardDishes.itemClickListenerIncrease = {
            val key = myCardDishes.myCardDishes[it.pos].id
            val defaultValue = myCardDishes.myCardDishes[it.pos].count

            prices[key] = prices.getOrDefault(key, defaultValue) + 1

            prices[key]?.let { pricesKey ->

                myCard.update(key.toString(), pricesKey).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        myCard.getAllMyCard()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ dishes ->
                                myCardDishes.myCardDishes.clear()
                                myCardDishes.myCardDishes.addAll(dishes)
                                _lists.value = AppStateMyCard.OnSuccess(dishes, myCardDishes)

                                totalPrice(dishes)
                            },
                                { error ->
                                    _lists.value =
                                        error.message?.let { errorMessage ->
                                            AppStateMyCard.Error(
                                                errorMessage
                                            )
                                        }

                                })
                    }, { error ->
                        _lists.value =
                            error.message?.let { errorMessage -> AppStateMyCard.Error(errorMessage) }
                    })

            }
        }

        myCardDishes.itemClickListenerDecrease = {
            val key = myCardDishes.myCardDishes[it.pos].id
            val defaultValue = myCardDishes.myCardDishes[it.pos].count

            prices[key] = prices.getOrDefault(key, defaultValue) - 1

            prices[key]?.let { pricesKey ->


                if (pricesKey == 0) {
                    myCard.delete(myCardDishes.myCardDishes[it.pos])
                        .observeOn(AndroidSchedulers.mainThread()).subscribe({
                            myCard.getAllMyCard()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    myCardDishes.myCardDishes.clear()
                                    myCardDishes.myCardDishes.addAll(it)
                                    _lists.value = AppStateMyCard.OnUpdate(myCardDishes)

                                    totalPrice(it)
                                },
                                    {

                                        _lists.value =
                                            it.message?.let { it1 -> AppStateMyCard.Error(it1) }
                                    })
                        }, {
                            _lists.value = it.message?.let { it1 -> AppStateMyCard.Error(it1) }
                        })
                } else {

                    myCard.update(key.toString(), pricesKey)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            myCard.getAllMyCard()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ dishes ->
                                    myCardDishes.myCardDishes.clear()
                                    myCardDishes.myCardDishes.addAll(dishes)
                                    _lists.value = AppStateMyCard.OnUpdate(myCardDishes)

                                    totalPrice(dishes)
                                },
                                    { error ->

                                        _lists.value =
                                            error.message?.let { errorMessage ->
                                                AppStateMyCard.Error(
                                                    errorMessage
                                                )
                                            }
                                    })
                        }, { error ->
                            _lists.value = error.message?.let { errorMessage ->
                                AppStateMyCard.Error(errorMessage)
                            }
                        })
                }

            }
        }

        getAllDishesFromCard()
    }

    @SuppressLint("CheckResult")
    private fun getAllDishesFromCard() {
        myCard.getAllMyCard()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ dishes ->
                myCardDishes.myCardDishes.clear()
                myCardDishes.myCardDishes.addAll(dishes)
                totalPrice(dishes)
                _lists.value = AppStateMyCard.OnSuccess(myCardDishes.myCardDishes, myCardDishes)

            }, { error ->
                _lists.value =
                    error.message?.let { errorMessage -> AppStateMyCard.Error(errorMessage) }
            })
    }

    private fun totalPrice(myProducts: List<com.example.efoods.domain.entity.Dishe>) {
        var totalPrice = 0
        myProducts.forEach {
            totalPrice += it.price.toInt() * it.count
        }
        _lists.value = AppStateMyCard.TotalPrice(totalPrice.toString())

    }

}