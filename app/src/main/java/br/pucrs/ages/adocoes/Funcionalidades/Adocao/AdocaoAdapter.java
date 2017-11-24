package br.pucrs.ages.adocoes.Funcionalidades.Adocao;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.Model.MenorMidia;
import br.pucrs.ages.adocoes.Model.RefMidia;
import br.pucrs.ages.adocoes.R;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by israeldeorce on 20/09/17.
 */

public class AdocaoAdapter extends RecyclerView.Adapter<AdocaoAdapter.MenorItemView> {

    private Activity activity;
    private List<Menor> items = new ArrayList<>();
    private AdocaoAdapter.OnMenorSelectedListener mOnMenorSelectedListener;
//    private AdocaoAdapter.OnMenorSelectedListener mOnMenorCancelarAdocaoListener;
    private boolean isLogged;

    public interface OnMenorSelectedListener {
        void OnMenorItemSelected(Menor menor, int position);
    }

    public void setListener(AdocaoAdapter.OnMenorSelectedListener selectListener) {
        this.mOnMenorSelectedListener = selectListener;
    }

    public AdocaoAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<Menor> items) {
        if(items != null) {
            this.items = items;
            notifyDataSetChanged();
        }
    }

    @Override
    public AdocaoAdapter.MenorItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_vertical_item, parent, false);

        return new AdocaoAdapter.MenorItemView(view);
    }

    @Override
    public void onBindViewHolder(AdocaoAdapter.MenorItemView holder, int position) {
        final AdocaoAdapter.MenorItemView itemView = holder;
        final Menor menor = items.get(position);

        isLogged = UserBusiness.getInstance().isLogged();

        if (menor != null) {
            itemView.tvNome.setText(menor.getNome());
        }


        if(isLogged) {
            for (RefMidia midia : menor.getMidias()) {
                if (midia.isPrincipal()) {
                    String token = UserBusiness.getInstance().getAccessToken();
                    RestUtil.getMenoresEndPoint().menorMidia(menor.getId(), midia.getId(), token).enqueue(new Callback<MenorMidia>() {
                        @Override
                        public void onResponse(Call<MenorMidia> call, Response<MenorMidia> response) {
                            MenorMidia midia = response.body();

                            if (midia != null) {
                                byte[] imageAsBytes = Base64.decode(midia.getConteudo().getBytes(), Base64.DEFAULT);
                                Bitmap imgBitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                                itemView.imgFoto.setImageBitmap(imgBitmap);
                            }
                        }

                        @Override
                        public void onFailure(Call<MenorMidia> call, Throwable t) {

                        }
                    });
                    break;
                }
            }
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
