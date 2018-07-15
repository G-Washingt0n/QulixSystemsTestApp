package com.gmail.martsulgp.qulixsystemstestapp.model.mappers

import com.gmail.martsulgp.qulixsystemstestapp.model.entity.Pagination
import com.gmail.martsulgp.qulixsystemstestapp.model.response.PaginationResponse

object PaginationMapper {
    fun map(paginationResponse: PaginationResponse?) = Pagination(
            totalCount = paginationResponse?.totalCount ?: 0,
            count = paginationResponse?.count ?: 0,
            offset = paginationResponse?.offset ?: 0
    )
}