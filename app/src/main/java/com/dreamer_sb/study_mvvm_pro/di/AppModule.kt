package com.dreamer_sb.study_mvvm_pro.di

import com.dreamer_sb.study_mvvm_pro.BuildConfig
import com.dreamer_sb.study_mvvm_pro.adapter.MainSearchRecyclerViewAdapter
import com.dreamer_sb.study_mvvm_pro.data.api.KakaoServiceApi
import com.dreamer_sb.study_mvvm_pro.data.api.Url
import com.dreamer_sb.study_mvvm_pro.model.DataModel
import com.dreamer_sb.study_mvvm_pro.model.DataModelImpl
import com.dreamer_sb.study_mvvm_pro.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    // ViewModel
    viewModel { MainViewModel(get()) }

    // Data
    factory<DataModel> { DataModelImpl(get()) }


    // API
    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if(BuildConfig.DEBUG){
                        HttpLoggingInterceptor.Level.BODY
                    }
                    else{
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }
    single<KakaoServiceApi>{
        Retrofit.Builder().baseUrl(Url.KAKAO_SEARCH_API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 추가하면서 create() 매개변수안에 클래스 넣어줘야함
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(KakaoServiceApi::class.java)

    }

    factory { MainSearchRecyclerViewAdapter() }

}