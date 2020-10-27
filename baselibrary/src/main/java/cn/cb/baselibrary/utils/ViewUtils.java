package cn.cb.baselibrary.utils;

import android.content.Context;
import android.graphics.Outline;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.recyclerview.widget.RecyclerView;

public class ViewUtils {

    /**
     * 裁剪 View 圆角
     *
     * @param view
     */
    public static void setOutline(View view) {
        view.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20);
            }
        });
        view.setClipToOutline(true);
    }

    /**
     * 根据item高度和最大值，设置list高度
     *
     * @param rv
     * @param context
     * @param itemDpValue 单个item高度
     * @param maxItem     展示最大item数量
     */
    public static void resetRVItemHeight(RecyclerView rv, Context context, int itemDpValue, int maxItem) {
        ViewGroup.LayoutParams lp = rv.getLayoutParams();
        if (rv.getAdapter().getItemCount() > maxItem) {
            lp.height = DensityUtil.dip2px(context, itemDpValue * maxItem);
        } else {
            lp.height = DensityUtil.dip2px(context, itemDpValue * rv.getAdapter().getItemCount());
        }
        rv.setLayoutParams(lp);
    }
}
