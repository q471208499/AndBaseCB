package cn.cb.andbase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.cb.andbase.R

class PathAdapter(context: Context, list: List<Map<String, String>>) :
    RecyclerView.Adapter<PathAdapter.PathViewHolder>() {
    var mContext: Context = context
    var mList: List<Map<String, String>> = list

    class PathViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pathKey: TextView = itemView.findViewById(R.id.path_item_key)
        val pathValue: TextView = itemView.findViewById(R.id.path_item_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_path, parent, false)
        return PathViewHolder(view)
    }

    override fun onBindViewHolder(holder: PathViewHolder, position: Int) {
        val map: Map<String, String> = mList[position]
        holder.pathKey.text = map["pathName"]
        holder.pathValue.text = map["pathStr"]
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}