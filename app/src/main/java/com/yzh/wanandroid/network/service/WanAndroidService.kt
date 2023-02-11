/*
 * CopyRight (C) 2022 Tencent. All rights reserved.
 */
package com.yzh.wanandroid.network.service

import com.yzh.wanandroid.network.response.ArticleResponse
import com.yzh.wanandroid.network.response.BannerCard
import com.yzh.wanandroid.network.response.Friend
import com.yzh.wanandroid.network.response.HttpResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author baronyang@tencent.com
 * @since 2022/12/25 16:16
 */
interface WanAndroidService {

    @GET("article/list/{index}/json")
    fun getArticleList(@Path("index") index: Int): Call<HttpResult<ArticleResponse>>

    @GET("banner/json")
    fun getBannerList(): Call<HttpResult<List<BannerCard>>>

    @GET("friend/json")
    fun getFriendList(): Call<HttpResult<List<Friend>>>
}