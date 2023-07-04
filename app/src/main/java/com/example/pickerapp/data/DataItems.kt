package com.example.pickerapp.data

import com.google.gson.annotations.SerializedName

data class DataItems(
    @SerializedName("items")
    val selectedItem: List<Item>
)
