package br.pucrs.ages.adocoes.ListagemDeMenores;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by kuquert on 29/05/17.
 */

public class ListaHorizontalAdapter extends RecyclerView.Adapter<ListaHorizontalAdapter.MenorItemView> {

    public interface OnMenorSelectedListener {
        void OnMenorItemSelected(Menor menor);
    }

    private OnMenorSelectedListener mOnMenorSelectedListener;
    private OnMenorSelectedListener mOnMenorFavoritarListener;

    public void setListener(OnMenorSelectedListener listener) {
        this.mOnMenorSelectedListener = listener;
    }

    public void setOnMenorFavoritarListener(OnMenorSelectedListener onMenorFavoritarListener) {
        mOnMenorFavoritarListener = onMenorFavoritarListener;
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
    public ListaHorizontalAdapter.MenorItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_horizontal_item, parent, false);
        return new MenorItemView(view);
    }

    @Override
    public void onBindViewHolder(ListaHorizontalAdapter.MenorItemView holder, int position) {
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

    class MenorItemView extends RecyclerView.ViewHolder  {

        TextView tvNome;
        TextView tvSexo;
        TextView tvIdade;
        ImageView imgFoto;
        ImageButton btnFavoritar;

        MenorItemView(View view) {
            super(view);
            tvNome = (TextView) view.findViewById(R.id.tv_nome);
            tvIdade = (TextView) view.findViewById(R.id.tv_idade);
            tvSexo = (TextView) view.findViewById(R.id.tv_sexo);
            imgFoto = (ImageView) view.findViewById(R.id.img_menor);
            btnFavoritar = (ImageButton) view.findViewById(R.id.btn_favoritar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Menor menorItem = items.get(getAdapterPosition());
                    mOnMenorSelectedListener.OnMenorItemSelected(menorItem);

                }
            });

            btnFavoritar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Menor menorItem = items.get(getAdapterPosition());
                    mOnMenorFavoritarListener.OnMenorItemSelected(menorItem);
                }
            });

        }


    }
}
