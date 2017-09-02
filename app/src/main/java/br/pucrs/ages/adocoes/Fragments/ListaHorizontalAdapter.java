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
import java.util.Objects;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by kuquert on 29/05/17.
 */

public class ListaHorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnMenorSelectedListener {
        void OnMenorItemSelected(Object transaction);
    }

    private OnMenorSelectedListener mOnMenorSelectedListener;
    private OnMenorSelectedListener mOnMenorFavoritarListener;

    public void setListener(OnMenorSelectedListener listener, OnMenorSelectedListener favoritar) {
        this.mOnMenorSelectedListener = listener;
        this.mOnMenorFavoritarListener = favoritar;
    }

    private Activity activity;
    //Title, Image
    private ArrayList<Menor> items;

    public ListaHorizontalAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(ArrayList<Menor> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_horizontal_item, parent, false);
        return new MenorItemView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MenorItemView itemView = (MenorItemView) holder;
        if (items.get(position).getNome()  != null) {
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class MenorItemView extends RecyclerView.ViewHolder  {

        TextView tvNome;
        TextView tvSexo;
        TextView tvIdade;
        ImageView imgFoto;
        Button btnDetalhes;
        Button btnFavoritar;

        MenorItemView(View view) {
            super(view);
            tvNome = (TextView) view.findViewById(R.id.tv_nome);
            tvIdade = (TextView) view.findViewById(R.id.tv_idade);
            tvSexo = (TextView) view.findViewById(R.id.tv_sexo);
            imgFoto = (ImageView) view.findViewById(R.id.img_menor);
            btnDetalhes = (Button) view.findViewById(R.id.btn_detalhes);
            btnFavoritar = (Button) view.findViewById(R.id.btn_favoritar);


            btnDetalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Object menorItem = items.get(getAdapterPosition());
                    mOnMenorSelectedListener.OnMenorItemSelected(menorItem);

                }
            });

            btnFavoritar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Object menorItem = items.get(getAdapterPosition());
                    mOnMenorFavoritarListener.OnMenorItemSelected(menorItem);
                }
            });

        }


    }
}
