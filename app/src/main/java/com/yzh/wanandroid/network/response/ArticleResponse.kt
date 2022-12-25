package com.yzh.wanandroid.network.response

/**
 * CopyRight (C) 2022 Tencent. All rights reserved.
 *
 * @author baronyang@tencent.com
 * @date 2022/12/25 16:16
 */
data class HttpResult<T>(
    val data: T,
    val errorCode: Int = 0,
    val errorMsg: String = ""
)

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
    val adminAdd: Boolean,
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    val envelopePic: String,
    val fresh: Boolean,
    val host: String,
    val id: Int,
    val isAdminAdd: Boolean,
    val link: String = "",
    val niceDate: String,
    val niceShareDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Any>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
)