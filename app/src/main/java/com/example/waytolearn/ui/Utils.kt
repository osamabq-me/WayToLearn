package com.example.waytolearn.ui

import androidx.annotation.DrawableRes
import com.example.waytolearn.R


object Utils {
    val category = listOf(
        Category(title = "English", resId = R.drawable.uk, id = 0),
        Category(title = "Indonesian", resId = R.drawable.indonesia, id = 1),
        Category(title = "Arabic", resId = R.drawable.dhad, id = 2),
        Category(title = "Türkçe", resId = R.drawable.turkey, id = 3),
        Category(title = "Spanish", resId = R.drawable.spain, id = 4),
        Category(title = "All", resId =R.drawable.globe, id = 10001)
    )
}

data class Category(
    @DrawableRes val resId: Int = -1,
    val title: String = "",
    val id: Int = -1,
)