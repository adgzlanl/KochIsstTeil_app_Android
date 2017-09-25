package com.adiguzel.anil.kochisstteil;

/**
 * Created by lenovo on 25.09.2017.
 */

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by lenovo on 25.09.2017.
 */

public class SaladAdapter extends RecyclerView.Adapter<SaladAdapter.ViewHolder> {

    private List<MenuList> listItems;
    private Context context;

    public SaladAdapter(List<MenuList> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MenuList listItem=listItems.get(position);
        holder.textViewHead.setText(listItem.getName());
        holder.textViewDescription.setText(listItem.getDesc());
        holder.textViewDuration.setText(listItem.getDuration());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDescription;
        public TextView textViewDuration;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead=(TextView) itemView.findViewById(R.id.item_title);
            textViewDescription=(TextView) itemView.findViewById(R.id.item_detail);
            textViewDuration=(TextView) itemView.findViewById(R.id.item_duration);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }
    }



}

