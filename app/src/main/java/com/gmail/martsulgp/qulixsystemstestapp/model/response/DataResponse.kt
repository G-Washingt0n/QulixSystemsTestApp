package com.gmail.martsulgp.qulixsystemstestapp.model.response

import com.google.gson.annotations.SerializedName

data class DataResponse(
        val user: UserResponse?,
        @SerializedName("images")
        val image: ImageResponse?,
        val title: String?

)