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

        RecyclerView recyclerView = findViewById(R.id.recycler_demo1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new MyDividerItemDecoration().setDrawOuterBorder(true));
        recyclerView.setAdapter(new RecyclerAdapter());


        RecyclerView recyclerView2 = findViewById(R.id.recycler_demo2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView2.addItemDecoration(new MyDividerItemDecoration().setDrawOuterBorder(true));
        recyclerView2.setAdapter(new RecyclerAdapter());


        RecyclerView recyclerView3 = findViewById(R.id.recycler_demo3);
        recyclerView3.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView3.addItemDecoration(new MyDividerItemDecoration().setDrawOuterBorder(false).setColorString("#ff9d1d"));
        recyclerView3.setAdapter(new RecyclerAdapter());


        new MyDividerItemDecoration()
                .setColorString("#ff9d1d")//自定义颜色
                .setColorString5()//固定自定义颜色，逐步加深
                .setColorString15()//固定自定义颜色，逐步加深
                .setColorString35()//固定自定义颜色，逐步加深
                .setColorString50()//固定自定义颜色，逐步加深
                .setDrawOuterBorder(true)//设置外边框:默认false
                .setHasTitle(true)//带标题item:默认false
                .setLineWidth(5);//分割线段宽度:默认2

        /*RecyclerView recyclerView4 = findViewById(R.id.recycler_demo4);
        recyclerView4.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
        recyclerView4.addItemDecoration(new MyDividerItemDecoration().setDrawOuterBorder(false));
        recyclerView4.setAdapter(new RecyclerAdapter());*/

    }
}