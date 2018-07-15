package com.gmail.martsulgp.qulixsystemstestapp.model.mappers

import com.gmail.martsulgp.qulixsystemstestapp.model.entity.Data
import com.gmail.martsulgp.qulixsystemstestapp.model.response.DataResponse

object DataMapper {
    fun map(dataResponse: List<DataResponse>?): List<Data> = dataResponse?.map { map(it) }
            ?: emptyList()

    fun map(dataResponse: DataResponse?) = Data(
            user = UserMapper.map(userResponse = dataResponse?.user),
            image = ImageMapper.map(imageResponse = dataResponse?.image),
            title = dataResponse?.title ?: ""
    )
}