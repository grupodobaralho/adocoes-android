package br.pucrs.ages.adocoes.fragments;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.R;

/**
 * Created by kuquert on 29/05/17.
 */

public class FirstRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private ArrayList<String> items;

    public FirstRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(ArrayList<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_holder, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemView itemView = (ItemView) holder;
        itemView.textView.setText(items.get(position));
        int a = activity.getResources().getColor(R.color.colorAccent);
        int b = activity.getResources().getColor(R.color.colorPrimary);

        itemView.textView.setBackgroundColor(position%2==0 ? b : a);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ItemView extends RecyclerView.ViewHolder {

        TextView textView;

        ItemView(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
        }
    }
}
