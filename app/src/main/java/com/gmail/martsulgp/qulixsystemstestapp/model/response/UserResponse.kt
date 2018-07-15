package com.gmail.martsulgp.qulixsystemstestapp.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
        @SerializedName("avatar_url")
        val avatar: String?,
        @SerializedName("profile_url")
        val profileUrl: String?,
        @SerializedName("username")
        val userName: String?,
        @SerializedName("display_name")
        val userRealName: String?
)