package com.dreamer_sb.study_mvvm_pro.model

import com.dreamer_sb.study_mvvm_pro.data.enum.KakaoSearchSortEnum
import com.dreamer_sb.study_mvvm_pro.data.response.ImageSearchResponse
import io.reactivex.Single

interface DataModel {
    fun getData(query:String, sort: KakaoSearchSortEnum, page:Int, size:Int): Single<ImageSearchResponse>
}
