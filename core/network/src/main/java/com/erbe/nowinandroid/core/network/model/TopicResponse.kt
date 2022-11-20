package com.erbe.nowinandroid.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopicResponse(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "follower")
    val follower: Int?,
    @field:Json(name = "story")
    val story: Int?,
    @field:Json(name = "url")
    val url: String?
)