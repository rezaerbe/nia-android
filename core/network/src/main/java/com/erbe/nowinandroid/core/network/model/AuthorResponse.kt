package com.erbe.nowinandroid.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorResponse(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "bio")
    val bio: String?,
    @field:Json(name = "imageUrl")
    val imageUrl: String?,
    @field:Json(name = "follower")
    val follower: Int?,
    @field:Json(name = "url")
    val url: String?
)