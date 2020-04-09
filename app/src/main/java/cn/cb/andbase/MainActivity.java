package cn.cb.andbase;

import android.os.Bundle;
import android.os.Handler;

import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.widget.PullLoadMoreRecyclerView;


public class MainActivity extends BaseActivity {
    PullLoadMoreRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBarView();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLinearLayout();
        recyclerView.setAdapter(new RecyclerAdapter());
        recyclerView.setPushRefreshEnable(true);

        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setPullLoadMoreCompleted();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setPullLoadMoreCompleted();
                    }
                }, 2000);
            }
        });
    }
}
