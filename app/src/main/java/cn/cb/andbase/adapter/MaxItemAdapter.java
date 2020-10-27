package cn.cb.andbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cn.cb.andbase.R;

public class MaxItemAdapter extends RecyclerView.Adapter<MaxItemAdapter.MaxItemViewHolder> {
    private Context mContext;

    public MaxItemAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public MaxItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_max_item, parent, false);
        return new MaxItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaxItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class MaxItemViewHolder extends RecyclerView.ViewHolder {
        public MaxItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
