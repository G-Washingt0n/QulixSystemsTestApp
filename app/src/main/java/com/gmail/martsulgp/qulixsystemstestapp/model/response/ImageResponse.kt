package com.gmail.martsulgp.qulixsystemstestapp.model.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(
        @SerializedName("fixed_height_small_still")
        val smallImageResponse: SmallImageResponse?
)