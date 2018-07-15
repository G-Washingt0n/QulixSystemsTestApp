package com.gmail.martsulgp.qulixsystemstestapp.model.response

import com.google.gson.annotations.SerializedName

data class PaginationResponse(
        @SerializedName("total_count")
        val totalCount: Int?,
        val count: Int?,
        val offset: Int?
)