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

public class CombinationAdapter extends RecyclerView.Adapter<CombinationAdapter.ViewHolder> {

    private List<CombinationList> listItems;
    private Context context;

    public CombinationAdapter(List<CombinationList> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.combination_card_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CombinationList listItem=listItems.get(position);
        holder.textViewMenuName.setText(listItem.getMname());
        holder.textViewMainMenu.setText(listItem.getMmenu());
        holder.textViewDuration.setText(listItem.getDuration());
        holder.textViewSubMenu.setText(listItem.getSmenu());
        holder.textViewDesert.setText(listItem.getDesert());
        holder.textViewSoup.setText(listItem.getSoup());
        holder.textViewSalad.setText(listItem.getSalad());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewMenuName;
        public TextView textViewMainMenu;
        public TextView textViewSubMenu;
        public TextView textViewDesert;
        public TextView textViewSoup;
        public TextView textViewSalad;
        public TextView textViewDuration;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewMenuName=(TextView) itemView.findViewById(R.id.item_title);
            textViewMainMenu=(TextView) itemView.findViewById(R.id.item_detail);
            textViewDuration=(TextView) itemView.findViewById(R.id.item_duration);
            textViewSubMenu=(TextView) itemView.findViewById(R.id.item_submenu);
            textViewDesert=(TextView) itemView.findViewById(R.id.item_desert);
            textViewSalad=(TextView) itemView.findViewById(R.id.item_salad);
            textViewSoup=(TextView) itemView.findViewById(R.id.item_soup);
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

