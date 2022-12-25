/*
 * CopyRight (C) 2022 Tencent. All rights reserved.
 */
package com.yzh.wanandroid.repository

import android.util.Log
import com.yzh.wanandroid.network.response.Article
import com.yzh.wanandroid.network.response.ArticleResponse
import com.yzh.wanandroid.network.response.HttpResult
import com.yzh.wanandroid.network.service.WanAndroidService
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author baronyang@tencent.com
 * @since 2022/12/25 16:16
 */
class MainActivityRepository {

    private val _articleList = MutableStateFlow<List<Article>>(emptyList())
    val list: Flow<List<Article>> = _articleList

    fun loadData() {
        wanAndroidService.getArticleList(0).enqueue(object : Callback<HttpResult<ArticleResponse>?> {
            override fun onResponse(
                call: Call<HttpResult<ArticleResponse>?>,
                response: Response<HttpResult<ArticleResponse>?>
            ) {
                Log.i(TAG, "onResponse: ${response.body()}")
                MainScope().launch {
                    _articleList.emit(response.body()?.data?.datas?: emptyList())
                }
            }

            override fun onFailure(call: Call<HttpResult<ArticleResponse>?>, t: Throwable) {
            }

        })
    }

    companion object {
        private const val TAG: String = "MainActivityRepository"
        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val wanAndroidService by lazy {
            retrofit.create(WanAndroidService::class.java)
        }
    }
}