package com.example.androiddevchallenge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.Cat
import androidx.compose.runtime.setValue

class MainViewModel : ViewModel() {
    val cats by mutableStateOf(
        listOf(
            Cat(
                "Anan",
                "Male",
                "Two month old",
                "4kg",
                "He is so Beautiful and long hair",
                R.drawable.img_cat_anan
            ),
            Cat(
                "Bily",
                "Femal",
                "Six month",
                "1.75kg",
                "She is white cat",
                R.drawable.img_cat_bily
            ),
            Cat(
                "Cloudy",
                "Male",
                "Eight month",
                "2kg",
                "He is cute",
                R.drawable.img_cat_cloudy
            ),
            Cat(
                "Kitora",
                "Male",
                "One year three month",
                "3kg",
                "He has beautiful eyes ",
                R.drawable.img_cat_kitora
            ),
            Cat(
                "Kiki",
                "Male",
                "One month",
                "0.65kg",
                "He is so small and cute",
                R.drawable.img_cat_kiki
            ),
            Cat(
                "Lisa",
                "Female",
                "One year three month",
                "2.65kg",
                "Her body is long",
                R.drawable.img_cat_lisa
            ),
            Cat(
                "Pina",
                "Female",
                "One year three month",
                "2.65kg",
                "She body is long",
                R.drawable.img_cat_pina
            ),
            Cat(
                "Soda",
                "Male",
                "One month",
                "0.43kg",
                "His ear is cute",
                R.drawable.img_cat_soda
            ),
            Cat(
                "Tatami",
                "Male",
                "Four month",
                "1.56kg",
                "He like sleeping",
                R.drawable.img_cat_tatami
            ),

            )
    )

    var currentCat: Cat? by mutableStateOf(null)


    fun showDetails(cat: Cat) {
        this.currentCat = cat
    }

    fun closeDetails() {
        this.currentCat = null
    }

}