package com.l0122077.ikhsanari.responsi1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val name: String,
    val desc: String,
    val img: Int
) : Parcelable
