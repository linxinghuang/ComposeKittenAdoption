package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes

data class Cat(
    val name: String,
    val gender: String,
    val age: String,
    val weight: String,
    val describe: String,
    @DrawableRes val photo: Int
)

