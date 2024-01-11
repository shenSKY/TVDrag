package com.example.tvdrag;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.OnChildViewHolderSelectedListener;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class MainActivity extends FragmentActivity {

    MoveVerticalGridView verticalGridView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verticalGridView = findViewById(R.id.vertical_grid_view);
        verticalGridView.setSpanCount(5);
        verticalGridView.setSwapMode(false);
        verticalGridView.setVerticalSpacing(20);
        verticalGridView.setHorizontalSpacing(20);

        ArrayList beans = new ArrayList();
        for (int i = 0; i < 18; i++) {
            Bean bean = new Bean();
            bean.setText(String.valueOf(i));
            beans.add(bean);
        }

        ItemAdapter adapter = new ItemAdapter(this, beans);
        verticalGridView.setAdapter(adapter);

        verticalGridView.addOnChildViewHolderSelectedListener(new OnChildViewHolderSelectedListener() {
            @Override
            public void onChildViewHolderSelected(RecyclerView parent, RecyclerView.ViewHolder child, int position, int subposition) {
                super.onChildViewHolderSelected(parent, child, position, subposition);

                child.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (verticalGridView.isSwapMode == true) {
                            verticalGridView.setSwapMode(false);
                            child.itemView.setBackgroundColor(Color.BLUE);
                        }else {
                            child.itemView.setBackgroundColor(Color.YELLOW);
                            verticalGridView.setSwapMode(true);
                        }
                    }
                });
            }
        });
    }
}