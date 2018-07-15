package com.gmail.martsulgp.qulixsystemstestapp.model.mappers

import com.gmail.martsulgp.qulixsystemstestapp.model.entity.User
import com.gmail.martsulgp.qulixsystemstestapp.model.response.UserResponse

object UserMapper {
    fun map(userResponse: UserResponse?) = User(
            avatar = userResponse?.avatar ?: "",
            profileUrl = userResponse?.profileUrl ?: "",
            userName = userResponse?.userName ?: "",
            userRealName = userResponse?.userRealName ?: ""

    )

}
