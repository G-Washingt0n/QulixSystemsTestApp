package com.gmail.martsulgp.qulixsystemstestapp.model.mappers

import com.gmail.martsulgp.qulixsystemstestapp.model.entity.Image
import com.gmail.martsulgp.qulixsystemstestapp.model.response.ImageResponse

object ImageMapper {
    fun map(imageResponse: ImageResponse?) = Image(
            smallImage = SmallImageMapper.map(smallImageResponse = imageResponse?.smallImageResponse)
    )
}