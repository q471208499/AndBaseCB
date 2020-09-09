package cn.cb.andbase.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.cb.andbase.R;
import cn.cb.andbase.adapter.RecyclerAdapter;
import cn.cb.baselibrary.activity.BaseActivity;
import cn.cb.baselibrary.widget.MyDividerItemDecoration;

public class RecyclerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initBarView();
        boolean outerBorder = true;

        RecyclerView recyclerView = findViewById(R.id.recycler_demo1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new MyDividerItemDecoration().setDrawOuterBorder(outerBorder));
        recyclerView.setAdapter(new RecyclerAdapter());


        RecyclerView recyclerView2 = findViewById(R.id.recycler_demo2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView2.addItemDecoration(new MyDividerItemDecoration().setDrawOuterBorder(outerBorder));
        recyclerView2.setAdapter(new RecyclerAdapter());


        RecyclerView recyclerView3 = findViewById(R.id.recycler_demo3);
        recyclerView3.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView3.addItemDecoration(new MyDividerItemDecoration().setDrawOuterBorder(outerBorder).setColorString("#ff9d1d"));
        recyclerView3.setAdapter(new RecyclerAdapter());
//
//
//        RecyclerView recyclerView4 = findViewById(R.id.recycler_demo4);
//        recyclerView4.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
//        recyclerView4.addItemDecoration(new MyDividerItemDecoration().setDrawOuterBorder(outerBorder));
//        recyclerView4.setAdapter(new RecyclerAdapter());

    }
}