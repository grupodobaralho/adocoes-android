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

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by kuquert on 29/05/17.
 */

public class FirstRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnMenorSelectedListener {
        void OnMenorItemSelected(Object transaction);
    }

    private OnMenorSelectedListener mOnMenorSelectedListener;

    public void setListener(OnMenorSelectedListener listener) {
        this.mOnMenorSelectedListener = listener;
    }

    private Activity activity;
    //Title, Image
    private ArrayList<Menor> items;

    public FirstRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(ArrayList<Menor> items) {
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
        if (items.get(position).getNome()  != null) {
            itemView.textView.setText(items.get(position).getNome());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class MenorItemView extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView imageView;
        Button button;

        MenorItemView(View view) {
            super(view);
            itemView.setOnClickListener(this);
            textView = (TextView) view.findViewById(R.id.text_view);
            imageView = (ImageView) view.findViewById(R.id.image_view);
            button = (Button) view.findViewById(R.id.button);
        }

        @Override
        public void onClick(View v) {
            Object menorItem = items.get(getAdapterPosition());
            mOnMenorSelectedListener.OnMenorItemSelected(menorItem);
        }
    }
}
