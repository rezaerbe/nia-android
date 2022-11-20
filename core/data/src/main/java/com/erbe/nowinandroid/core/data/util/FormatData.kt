package com.erbe.nowinandroid.core.data.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun formatPublishDate(publishDate: String?): String? {
    return publishDate?.let { date ->
        LocalDate.parse(date).format(DateTimeFormatter.ofPattern("MMM d, yyyy"))
    }
}

fun formatReadTime(readTime: Int?): String? {
    return readTime?.let { time ->
        "$time min read"
    }
}

fun formatFollower(follower: Int?): String? {
    return follower?.let { count ->
        "$count Followers"
    }
}

fun formatStory(story: Int?): String? {
    return story?.let { count ->
        "$count Stories"
    }
}