package com.erbe.nowinandroid.core.data.model

import com.erbe.nowinandroid.core.data.util.formatFollower
import com.erbe.nowinandroid.core.data.util.formatStory
import com.erbe.nowinandroid.core.network.model.TopicResponse

data class Topic(
    val id: Int?,
    val name: String?,
    val follower: String?,
    val story: String?,
    val url: String?
)

fun TopicResponse.asDomain() = Topic(
    id,
    name,
    formatFollower(follower),
    formatStory(story),
    url
)