package com.erbe.nowinandroid.core.network

import com.erbe.nowinandroid.core.network.datasource.NiaRemoteDataSourceImpl
import com.erbe.nowinandroid.core.network.model.ArticleResponse
import com.erbe.nowinandroid.core.network.service.NiaService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class NiaRemoteDataSourceTest {

    private val testDispatcher = StandardTestDispatcher()
    private val mockWebServer = MockWebServer()
    private lateinit var dataSource: NiaRemoteDataSourceImpl

    @Before
    fun setup() {
        val niaService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NiaService::class.java)

        dataSource = NiaRemoteDataSourceImpl(
            ioDispatcher = testDispatcher,
            niaService = niaService
        )
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetch_article_with_response_200() = runTest(testDispatcher) {
        val response = MockResponse()
            .setBody("""[{"id":1,"title":"Now in Android #73","description":"Android Dev Summit: Platform track, Health Connect, Now in Android on Google Play, user choice billing, lazy layouts","content":"","imageUrl":"https://miro.medium.com/0*xEuK15gUA0YXznsR","publishDate":"2022-11-17","readTime":5,"author":1,"topics":[1,2],"url":"https://medium.com/androiddevelopers/now-in-android-73-f0effa197dfb"}]""")
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        val flow = dataSource.getArticles()
        launch {
            flow.collect {
                assertEquals(
                    listOf(
                        ArticleResponse(
                            id = 1,
                            title = "Now in Android #73",
                            description = "Android Dev Summit: Platform track, Health Connect, Now in Android on Google Play, user choice billing, lazy layouts",
                            content = "",
                            imageUrl = "https://miro.medium.com/0*xEuK15gUA0YXznsR",
                            publishDate = "2022-11-17",
                            readTime = 5,
                            author = 1,
                            topics = listOf(1, 2),
                            url = "https://medium.com/androiddevelopers/now-in-android-73-f0effa197dfb"
                        )
                    ), it
                )
            }
        }
    }
}