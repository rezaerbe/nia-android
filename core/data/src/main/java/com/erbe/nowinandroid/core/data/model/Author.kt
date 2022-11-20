package com.erbe.nowinandroid.core.data.model

import com.erbe.nowinandroid.core.data.util.formatFollower
import com.erbe.nowinandroid.core.network.model.AuthorResponse

data class Author(
    val id: Int?,
    val name: String?,
    val bio: String?,
    val imageUrl: String?,
    val follower: String?,
    val url: String?
)

fun AuthorResponse.asDomain() = Author(
    id,
    name,
    bio,
    imageUrl,
    formatFollower(follower),
    url
)