package com.dreamer_sb.study_mvvm_pro.view

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dreamer_sb.study_mvvm_pro.R
import com.dreamer_sb.study_mvvm_pro.adapter.MainSearchRecyclerViewAdapter
import com.dreamer_sb.study_mvvm_pro.databinding.ActivityMainBinding
import com.dreamer_sb.study_mvvm_pro.viewmodel.BaseViewModel
import com.dreamer_sb.study_mvvm_pro.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/** 참고 주소
https://deque.tistory.com/108
*/

/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

 */



class MainActivity : BaseView<ActivityMainBinding, BaseViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModel()
//        get() = TODO("Not yet implemented")

    private val mainSearchRecyclerViewAdapter : MainSearchRecyclerViewAdapter by inject()

//    private val binding by lazy{
//        ActivityMainBinding.inflate(layoutInflater)
//    }

    override fun initStartView() {
        viewDataBinding.mainActivitySearchRecyclerView.run {
            adapter = mainSearchRecyclerViewAdapter
            layoutManager = StaggeredGridLayoutManager(3,1).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                orientation = StaggeredGridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.imageSearchResponseLiveData.observe(this, Observer {
            mainSearchRecyclerViewAdapter.clearImageItem()
            it.documents.forEach { document ->
                mainSearchRecyclerViewAdapter.addImageItem(document.image_url, document.doc_url)
            }
            mainSearchRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        viewDataBinding.mainActivitySearchButton.setOnClickListener {
            viewModel.getImageSearch(viewDataBinding.mainActivitySearchTextView.text.toString(), 1,80)
        }
    }

}