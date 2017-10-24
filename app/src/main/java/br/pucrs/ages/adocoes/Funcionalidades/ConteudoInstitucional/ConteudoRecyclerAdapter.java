package br.pucrs.ages.adocoes.Funcionalidades.ConteudoInstitucional;

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
 * Created by Bruno Cardoso on 24/06/2017.
 */

public class ConteudoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Activity activity;
    private ArrayList<String> items;

    public ConteudoRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(ArrayList<String> items) {
        if (items != null) {
            this.items = items;
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conteudo_institucional_item, parent, false);
        return new ConteudoItemView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ConteudoItemView itemView = (ConteudoItemView) holder;
        itemView.tvConteudo.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ConteudoItemView extends RecyclerView.ViewHolder {

        TextView tvConteudo;
        TextView tvTitulo;
        ImageView ivConteudo;

        ConteudoItemView(View view) {
            super(view);
            tvConteudo = (TextView) view.findViewById(R.id.tv_conteudo);
            tvTitulo = (TextView) view.findViewById(R.id.tv_titulo);
            ivConteudo = (ImageView) view.findViewById(R.id.image_view);
        }
    }



}





