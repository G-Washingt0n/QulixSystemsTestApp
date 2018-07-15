package com.gmail.martsulgp.qulixsystemstestapp.model.response

import com.google.gson.annotations.SerializedName

data class SmallImageResponse(
        @SerializedName("url")
        val imageUrl: String?,
        @SerializedName("width")
        val imageWidth: String?,
        @SerializedName("height")
        val imageHeight: String?,
        val size: Int?
)