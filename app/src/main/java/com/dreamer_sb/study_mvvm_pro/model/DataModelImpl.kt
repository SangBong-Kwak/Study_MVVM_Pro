package com.dreamer_sb.study_mvvm_pro.model

import com.dreamer_sb.study_mvvm_pro.BuildConfig
import com.dreamer_sb.study_mvvm_pro.data.api.KakaoServiceApi
import com.dreamer_sb.study_mvvm_pro.data.enum.KakaoSearchSortEnum
import com.dreamer_sb.study_mvvm_pro.data.response.ImageSearchResponse
import io.reactivex.Single

class DataModelImpl(private val service:KakaoServiceApi) : DataModel {



    override fun getData(
        query: String,
        sort: KakaoSearchSortEnum,
        page: Int,
        size: Int
    ): Single<ImageSearchResponse> {
        return service.searchImage(auth = "KakaoAK ${BuildConfig.KAKAO_REST_API_KEY}", query = query, sort = sort.sort, page = page, size = size)
    }
}