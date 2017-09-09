package br.pucrs.ages.adocoes.Fragments;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by lima on 04/09/17.
 */

public class ListaVerticalAdapter extends RecyclerView.Adapter<ListaVerticalAdapter.MenorItemView> {

    public interface OnMenorSelectedListener {
        void OnMenorItemSelected(Menor menor);
    }

    private OnMenorSelectedListener mOnMenorSelectedListener;
    private OnMenorSelectedListener mOnMenorFavoritarListener;

    public void setListener(OnMenorSelectedListener selectListener) {
        this.mOnMenorSelectedListener = selectListener;
    }

    public void setOnMenorFavoritarListener(OnMenorSelectedListener favoritarListener) {
        mOnMenorFavoritarListener = favoritarListener;
    }


    private Activity activity;
    //Title, Image
    private ArrayList<Menor> items;

    public ListaVerticalAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(ArrayList<Menor> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public ListaVerticalAdapter.MenorItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_vertical_item, parent, false);
        return new MenorItemView(view);
    }

    @Override
    public void onBindViewHolder(ListaVerticalAdapter.MenorItemView holder, int position) {
        MenorItemView itemView = holder;
        final Menor menor = items.get(position);
        if (menor != null) {
            itemView.tvNome.setText(menor.getNome());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MenorItemView extends RecyclerView.ViewHolder {

        TextView tvNome;
        TextView tvDetalhe;
        ImageView imgFoto;
        ImageButton btnFavoritar;
        RelativeLayout rlCell;

        MenorItemView(View view) {
            super(view);
            tvNome = (TextView) view.findViewById(R.id.tv_nome);
            tvDetalhe = (TextView) view.findViewById(R.id.tv_detalhe);
            imgFoto = (ImageView) view.findViewById(R.id.img_menor);
            btnFavoritar = (ImageButton) view.findViewById(R.id.btn_favoritar);
            rlCell = (RelativeLayout) view.findViewById(R.id.rl_cell);


            btnFavoritar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Menor menorItem = items.get(getAdapterPosition());
                    mOnMenorFavoritarListener.OnMenorItemSelected(menorItem);
                }
            });

            rlCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Menor menorItem = items.get(getAdapterPosition());
                    mOnMenorSelectedListener.OnMenorItemSelected(menorItem);
                }
            });
        }


    }
}