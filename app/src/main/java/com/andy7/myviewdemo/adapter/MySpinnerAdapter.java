package com.andy7.myviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andy7.myviewdemo.R;

import java.util.List;

/**
 * BeforeSaleFragment中的RecyclerAdapters
 * Created by andy7 on 2017/3/17.
 */

public class MySpinnerAdapter extends RecyclerView.Adapter<MySpinnerAdapter.MyViewHolder> {

    private List<String> list;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public MySpinnerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myspinner_list, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txtItemMyspinner.setText(list.get(position));
        holder.containerItemMyspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtItemMyspinner;
        LinearLayout containerItemMyspinner;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtItemMyspinner = (TextView) itemView.findViewById(R.id.txt_item_myspinner);
            containerItemMyspinner = (LinearLayout) itemView.findViewById(R.id.container_item_myspinner);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}




