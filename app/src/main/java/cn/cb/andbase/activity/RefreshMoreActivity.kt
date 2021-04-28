package cn.cb.andbase.activity

import android.os.Bundle
import cn.cb.andbase.R
import cn.cb.andbase.adapter.RecyclerAdapter
import cn.cb.baselibrary.activity.BaseActivity
import cn.cb.baselibrary.widget.PullLoadMoreRecyclerView

/**
 * 下拉刷新，加载更多
 */
class RefreshMoreActivity : BaseActivity() {
    private var refreshView: PullLoadMoreRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh_more)
        initBarView()

        refreshView = findViewById(R.id.refresh_view)
        refreshView?.setLinearLayout()
        refreshView?.setOnPullLoadMoreListener(Listener(this))
        refreshView?.setAdapter(RecyclerAdapter())
    }

    class Listener(activity: RefreshMoreActivity) : PullLoadMoreRecyclerView.PullLoadMoreListener {
        val activity = activity
        override fun onRefresh() {
            activity.refreshView?.postDelayed({ activity.refreshView?.setPullLoadMoreCompleted() }, 2000)
        }

        override fun onLoadMore() {
            activity.refreshView?.postDelayed({ activity.refreshView?.setPullLoadMoreCompleted() }, 2000)
        }
    }


}