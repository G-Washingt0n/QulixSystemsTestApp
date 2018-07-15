package com.gmail.martsulgp.qulixsystemstestapp.model.mappers

import com.gmail.martsulgp.qulixsystemstestapp.model.entity.GifsData
import com.gmail.martsulgp.qulixsystemstestapp.model.response.GifsDataResponse

object GifsDataMapper {
    fun map(gifsDataResponse: GifsDataResponse) = GifsData(
            data = DataMapper.map(gifsDataResponse.data),
            pagination = PaginationMapper.map(paginationResponse = gifsDataResponse.pagination)
    )
}