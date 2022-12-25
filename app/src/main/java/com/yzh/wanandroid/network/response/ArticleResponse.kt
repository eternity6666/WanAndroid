package com.yzh.wanandroid.network.response

/**
 * CopyRight (C) 2022 Tencent. All rights reserved.
 *
 * @author baronyang@tencent.com
 * @date 2022/12/25 16:16
 */
data class ArticleResponse(
    val curPage: Int = 0,
    val datas: List<Article>,
    val offset: Int = 0,
    val over: Boolean = true,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0,
)

data class Article(
    val id: Int,
    val link: String = "",
)