package cn.cb.andbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import cn.cb.andbase.R;

public class MyMenuAdapter extends RecyclerView.Adapter<MyMenuAdapter.MenuVewHolder> {

    private Context mContext;
    private List<Map<String, Object>> mList;
    private View.OnClickListener mListener;

    public MyMenuAdapter(Context context, List<Map<String, Object>> list, View.OnClickListener listener) {
        mContext = context;
        mList = list;
        mListener = listener;
    }

    @NonNull
    @Override
    public MenuVewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_menu, parent, false);
        return new MenuVewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuVewHolder holder, int position) {
        Map<String, Object> map = mList.get(position);
        holder.button.setTag(map);
        holder.button.setText((String) map.get("name"));
        holder.button.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MenuVewHolder extends RecyclerView.ViewHolder {
        Button button;

        public MenuVewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.item_btn);
        }
    }

}
