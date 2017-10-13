package br.pucrs.ages.adocoes.Funcionalidades.Favoritos;

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
 * Created by israeldeorce on 20/09/17.
 */

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.MenorItemView> {

    public interface OnMenorSelectedListener {
        void OnMenorItemSelected(Menor menor, int position);
    }

    private OnMenorSelectedListener mOnMenorSelectedListener;
    private OnMenorSelectedListener mOnMenorDesfavoritarListener;

    public void setListener(OnMenorSelectedListener selectListener) {
        this.mOnMenorSelectedListener = selectListener;
    }

    public void setOnMenorDesfavoritarListener(OnMenorSelectedListener favoritarListener) {
        mOnMenorDesfavoritarListener = favoritarListener;
    }


    private Activity activity;
    //Title, Image
    private ArrayList<Menor> items;

    public FavoritosAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(ArrayList<Menor> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public FavoritosAdapter.MenorItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_vertical_item, parent, false);
        ImageButton ib = (ImageButton) view.findViewById(R.id.btn_favoritar);
        ib.setImageResource(R.drawable.unlike_filled);
        return new FavoritosAdapter.MenorItemView(view);
    }

    @Override
    public void onBindViewHolder(FavoritosAdapter.MenorItemView holder, int position) {
        FavoritosAdapter.MenorItemView itemView = holder;
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
                    mOnMenorDesfavoritarListener.OnMenorItemSelected(menorItem, getAdapterPosition());
                }
            });

            rlCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Menor menorItem = items.get(getAdapterPosition());
                    mOnMenorSelectedListener.OnMenorItemSelected(menorItem, getAdapterPosition());
                }
            });
        }


    }
}
