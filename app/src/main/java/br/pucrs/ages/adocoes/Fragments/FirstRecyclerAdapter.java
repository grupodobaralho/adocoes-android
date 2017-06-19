package br.pucrs.ages.adocoes.Fragments;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menor_item_holder, parent, false);
        return new MenorItemView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MenorItemView itemView = (MenorItemView) holder;
        itemView.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class MenorItemView extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        Button button;

        MenorItemView(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text_view);
            imageView = (ImageView) view.findViewById(R.id.image_view);
            button = (Button) view.findViewById(R.id.button);
        }
    }
}
