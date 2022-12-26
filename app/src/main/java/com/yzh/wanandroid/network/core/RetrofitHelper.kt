package com.yzh.wanandroid.network.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * CopyRight (C) 2022 Tencent. All rights reserved.
 *
 * @author baronyang@tencent.com
 * @date 2022/12/26 16:16
 */
object RetrofitHelper {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}