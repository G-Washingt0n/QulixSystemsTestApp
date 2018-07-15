package com.gmail.martsulgp.qulixsystemstestapp.model.mappers

import com.gmail.martsulgp.qulixsystemstestapp.model.entity.SmallImage
import com.gmail.martsulgp.qulixsystemstestapp.model.response.SmallImageResponse

object SmallImageMapper {
    fun map(smallImageResponse: SmallImageResponse?) = SmallImage(
            imageUrl = smallImageResponse?.imageUrl ?: "",
            imageHeight = smallImageResponse?.imageHeight ?: "0",
            imageWidth = smallImageResponse?.imageWidth ?: "0",
            size = smallImageResponse?.size ?: 0
    )
}