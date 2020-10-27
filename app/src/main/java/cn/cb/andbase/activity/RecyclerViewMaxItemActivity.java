package cn.cb.andbase.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import cn.cb.andbase.R;
import cn.cb.andbase.adapter.MaxItemAdapter;
import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.utils.ViewUtils;
import cn.cb.baselibrary.widget.MyDividerItemDecoration;

public class RecyclerViewMaxItemActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_max_item);
        initBarView();
        bindView();
    }

    private void bindView() {
        RecyclerView recyclerView = findViewById(R.id.max_item_recycler);
        recyclerView.setAdapter(new MaxItemAdapter(this));
        recyclerView.addItemDecoration(new MyDividerItemDecoration());
        ViewUtils.resetRVItemHeight(recyclerView, this, 50, 5);
    }
}