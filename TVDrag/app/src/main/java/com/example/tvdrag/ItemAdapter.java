package com.example.tvdrag;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter{

    Context context;
    List<Bean> beans;

    public ItemAdapter(Context context, List<Bean> beans) {
        this.context = context;
        this.beans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        view.setBackgroundColor(Color.BLUE);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(beans.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public void moveItem(int from, int to) {
        Bean info = beans.remove(from);
        beans.add(to, info);
        // 该方法会触发到 SimpleItemAnimator 的 animateMove 方法
        // 利用 animateMove 实现平滑的移动效果
        // RecyclerView.ItemAnimator 的实现不在这里详细阐述，有兴趣可以去看 DefaultItemAnimator 的实现
        notifyItemMoved(from, to);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }
}
