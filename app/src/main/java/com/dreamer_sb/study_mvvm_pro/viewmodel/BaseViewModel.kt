package com.dreamer_sb.study_mvvm_pro.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    /**
     * RxJava 의 observing을 위한 부분.
     * addDisposable을 이용하여 추가하기만 하면 된다
     *
     * Model 에서 들어오는 Single<> 과 같은 RXJava 객체들의 Observing을 위한 부분
     * RxJava 의 Observable 들은 CompositeDisposable 에 추가해주고, 뷰모델이 없어질 때 지워줘야 함
     * Observalbe 들을 옵저빙 할 때 addDisposable() 을 사용
     *
     * 또한 ViewModel은 View와 생명주기를 공유하기 때문에 View가 Destroy 될 때 ViewModel 의 onCleared() 가 호출되게 됨
     */
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable : Disposable){
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }



}