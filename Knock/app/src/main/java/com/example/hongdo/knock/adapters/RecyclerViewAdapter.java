package com.example.hongdo.knock.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hongdo.knock.R;
import com.example.hongdo.knock.models.EventDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Win 8.1 Version 2 on 18/10/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    List<EventDay> list;

    public RecyclerViewAdapter(List<EventDay> list) {
        this.list = list;
    }

    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_event, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.RecyclerViewHolder holder, int position) {

        EventDay ev = list.get(position);
        holder.txtPickUpPoint.setText(ev.getStrPickUp());
        holder.txtDestination.setText(ev.getStrDes());
        holder.txtTimeArrival.setText(ev.getTimeArriver());
        holder.txtTimeDepart.setText(ev.getTimeDepart());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.relativeLayout.getVisibility() == View.GONE) {
                    holder.relativeLayout.setVisibility(View.VISIBLE);
                    holder.linearLayout.setVisibility(View.VISIBLE);
                    holder.tvLine1.setVisibility(View.VISIBLE);
                    holder.tvLine2.setVisibility(View.VISIBLE);
                } else {
                    holder.relativeLayout.setVisibility(View.GONE);
                    holder.linearLayout.setVisibility(View.GONE);
                    holder.tvLine1.setVisibility(View.GONE);
                    holder.tvLine2.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtPickUpPoint;
        TextView txtDestination;
        TextView txtTimeDepart;
        TextView txtTimeArrival;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;
        TextView tvLine1;
        TextView tvLine2;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtPickUpPoint = (TextView) itemView.findViewById(R.id.tv_detailPU);
            txtDestination = (TextView) itemView.findViewById(R.id.tv_detailDes);
            txtTimeDepart = (TextView) itemView.findViewById(R.id.tv_timeDepart);
            txtTimeArrival = (TextView) itemView.findViewById(R.id.tv_timeArrival);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rel_des);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearTimeItem);
            tvLine1 = (TextView) itemView.findViewById(R.id.tv_line01);
            tvLine2 = (TextView) itemView.findViewById(R.id.tv_line02);
        }
    }

    public void deleteData(ArrayList<EventDay> datas) {
        if (datas == null || datas.size() == 0)
            return;

        notifyDataSetChanged();

    }
}
