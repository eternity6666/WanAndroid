/*
 * CopyRight (C) 2022 Tencent. All rights reserved.
 */
package com.yzh.wanandroid.ext

import java.text.SimpleDateFormat

fun Long.formatToTime(): String {
    return if (this <= 0) {
        "00:00:00"
    } else {
        SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(this)
    }
}