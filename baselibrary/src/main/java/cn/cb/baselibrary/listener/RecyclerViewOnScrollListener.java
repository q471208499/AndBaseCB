package cn.cb.baselibrary.listener;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import cn.cb.baselibrary.widget.PullLoadMoreRecyclerView;

/**
 * Created by WuXiaolong
 * on 2015/7/7.
 * github:https://github.com/WuXiaolong/PullLoadMoreRecyclerView
 * weibo:http://weibo.com/u/2175011601
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public class RecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;

    public RecyclerViewOnScrollListener(PullLoadMoreRecyclerView pullLoadMoreRecyclerView) {
        this.mPullLoadMoreRecyclerView = pullLoadMoreRecyclerView;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int lastItem = 0;
        int firstItem = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int totalItemCount = layoutManager.getItemCount();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
            firstItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
            //Position to find the final item of the current LayoutManager
            lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
            if (lastItem == -1) lastItem = gridLayoutManager.findLastVisibleItemPosition();
        } else if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = ((LinearLayoutManager) layoutManager);
            firstItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            if (lastItem == -1) lastItem = linearLayoutManager.findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = ((StaggeredGridLayoutManager) layoutManager);
            // since may lead to the final item has more than one StaggeredGridLayoutManager the particularity of the so here that is an array
            // this array into an array of position and then take the maximum value that is the last show the position value
            int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
            staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(lastPositions);
            lastItem = findMax(lastPositions);
            firstItem = staggeredGridLayoutManager.findFirstVisibleItemPositions(lastPositions)[0];
        }
        if (firstItem == 0 || firstItem == RecyclerView.NO_POSITION) {
            if (mPullLoadMoreRecyclerView.getPullRefreshEnable())
                mPullLoadMoreRecyclerView.setSwipeRefreshEnable(true);
        } else {
            mPullLoadMoreRecyclerView.setSwipeRefreshEnable(false);
        }
        System.out.println("##mPullLoadMoreRecyclerView.getPushRefreshEnable()" + mPullLoadMoreRecyclerView.getPushRefreshEnable());
        System.out.println("##!mPullLoadMoreRecyclerView.isRefresh()" + !mPullLoadMoreRecyclerView.isRefresh());
        System.out.println("##mPullLoadMoreRecyclerView.isHasMore()" + mPullLoadMoreRecyclerView.isHasMore());
        System.out.println("##(lastItem == totalItemCount - 1)" + (lastItem == totalItemCount - 1));
        System.out.println("##!mPullLoadMoreRecyclerView.isLoadMore()" + !mPullLoadMoreRecyclerView.isLoadMore());
        System.out.println("##(dx > 0 || dy > 0)" + (dx > 0 || dy > 0));
        if (mPullLoadMoreRecyclerView.getPushRefreshEnable()//能否刷新
                && !mPullLoadMoreRecyclerView.isRefresh()//正在下拉刷新
                && mPullLoadMoreRecyclerView.isHasMore()//是否有更多内容
                && (lastItem == totalItemCount - 1)//最后一条
                && !mPullLoadMoreRecyclerView.isLoadMore()//正在上拉加载
                && (dx > 0 || dy > 0)) {
            System.out.println("##loadMore");
            mPullLoadMoreRecyclerView.setIsLoadMore(true);
            mPullLoadMoreRecyclerView.loadMore();
        }

    }

    //To find the maximum value in the array
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
