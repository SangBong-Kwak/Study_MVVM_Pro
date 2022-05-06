package com.dreamer_sb.study_mvvm_pro.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dreamer_sb.study_mvvm_pro.data.enum.KakaoSearchSortEnum
import com.dreamer_sb.study_mvvm_pro.data.response.ImageSearchResponse
import com.dreamer_sb.study_mvvm_pro.model.DataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val model: DataModel) : BaseViewModel() {


    private val _imageSearchResponeLiveData = MutableLiveData<ImageSearchResponse>()
    val imageSearchResponseLiveData: LiveData<ImageSearchResponse>
        get() = _imageSearchResponeLiveData

    fun getImageSearch(query: String, page: Int, size: Int) {
        addDisposable(model.getData(query, KakaoSearchSortEnum.Accuracy, page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                it.run {
                    if (documents.size > 0) {
                        Log.d(TAG, "documents : $documents")
                        _imageSearchResponeLiveData.postValue(this)
                    }
                    Log.d(TAG, "meta : $meta")
                }
            }, {
                Log.d(TAG, "response Error : ${it.message}")
            })
        )
    }

    companion object {
        private val TAG = "MainViewModel"
    }
}