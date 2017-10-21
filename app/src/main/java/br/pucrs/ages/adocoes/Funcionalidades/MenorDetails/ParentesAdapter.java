package br.pucrs.ages.adocoes.Funcionalidades.MenorDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by matheusvaccaro on 21/10/17.
 */

public class ParentesAdapter extends RecyclerView.Adapter<ParentesAdapter.ViewHolder> {


    private LayoutInflater mLayoutInflater;

    interface OnClickParente{
        void parenteClicked(Menor menor);
    }

    private OnClickParente onClickParente;
    private ArrayList<Menor> mMenores;

    public void setOnClickParente(OnClickParente onClickParente) {
        this.onClickParente = onClickParente;
    }

    public ParentesAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_parente_item_holder, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Menor menor = mMenores.get(position);
        holder.setData(menor);


    }

    @Override
    public int getItemCount() {
        return mMenores.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNomeTextView;
        private TextView mParentescoTextView;
        private ImageView mImageView;

        private ViewHolder(View itemView) {
            super(itemView);

            mNomeTextView = (TextView) itemView.findViewById(R.id.item_name);
            mParentescoTextView = (TextView) itemView.findViewById(R.id.item_parentage);
            mImageView = (ImageView) itemView.findViewById(R.id.item_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Menor menor = mMenores.get(position);

                    if (menor != null) {
                        onClickParente.parenteClicked(menor);
                    }
                }
            });
        }

        private void setData(Menor menor) {
            mNomeTextView.setText(menor.getNome());
//            mParentescoTextView.setText(  PEGAR PARENTESCO  );
//            mImageView.setImageResource(  PEGAR IMAGEM  );
        }
    }
}