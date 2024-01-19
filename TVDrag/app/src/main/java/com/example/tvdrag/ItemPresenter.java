package com.example.tvdrag;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.leanback.widget.Presenter;

public class ItemPresenter extends Presenter {

    Context context;

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        if (context == null) {
            context = parent.getContext();
        }

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        view.setBackgroundColor(Color.BLUE);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        ViewHolder vh = (ViewHolder) viewHolder;
        if (item instanceof Bean) {
            vh.textView.setText(((Bean) item).getText());
        }

    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

    public class ViewHolder extends Presenter.ViewHolder {

        TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.item_text);
        }
    }

}
