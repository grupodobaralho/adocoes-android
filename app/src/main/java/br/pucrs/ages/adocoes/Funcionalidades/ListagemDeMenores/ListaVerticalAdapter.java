package br.pucrs.ages.adocoes.Funcionalidades.ListagemDeMenores;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.Model.MenorInteresse;
import br.pucrs.ages.adocoes.Model.MenorMidia;
import br.pucrs.ages.adocoes.Model.RefMidia;
import br.pucrs.ages.adocoes.R;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lima on 04/09/17.
 */

public class ListaVerticalAdapter extends RecyclerView.Adapter<ListaVerticalAdapter.MenorItemView> {

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
    private List<Menor> items = new ArrayList<>();
    private List<MenorInteresse> listMenorInteresse;

    public ListaVerticalAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<Menor> items) {
        if (items != null) {
            this.items = items;
            notifyDataSetChanged();
        }
    }

    @Override
    public ListaVerticalAdapter.MenorItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_vertical_item, parent, false);
        return new MenorItemView(view);
    }

    @Override
    public void onBindViewHolder(ListaVerticalAdapter.MenorItemView holder, int position) {
        final MenorItemView itemView = holder;
        final Menor menor = items.get(position);
        final String token = UserBusiness.getInstance().getAccessToken();
        listMenorInteresse = new ArrayList<>();

        if (menor != null) {
            itemView.tvNome.setText(menor.getNome());
            itemView.tvDetalhe.setText("Sexo: "+menor.getSexo().toString());
            itemView.tvIdade.setText("Idade: "+Integer.toString(menor.getIdade()));

            RestUtil.getEuEndPoint().getInteresses(token, menor.getId()).enqueue(new Callback<List<MenorInteresse>>() {
                @Override
                public void onResponse(Call<List<MenorInteresse>> call, Response<List<MenorInteresse>> response) {
                    if (response.body() != null) {
                        listMenorInteresse = response.body();
                        if(listMenorInteresse.isEmpty());
                        else {
                            for(MenorInteresse mInteresse : listMenorInteresse){
                                if(mInteresse.getTipoInteresse().equals("favoritar"))
                                    itemView.btnFavoritar.setImageResource(R.drawable.icone_adocoes_black_white);
                            }
                        }
                    }else {
                        try {
                            Log.e("MenorInteresse", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<MenorInteresse>> call, Throwable t) {
                    Log.e("MenorInteresse", t.getLocalizedMessage(), t);
                }
            });
        }


        for (RefMidia midia : menor.getMidias()) {
            if (midia.isPrincipal()) {
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
        TextView tvIdade;

        MenorItemView(View view) {
            super(view);
            tvNome = (TextView) view.findViewById(R.id.tv_nome);
            tvDetalhe = (TextView) view.findViewById(R.id.tv_detalhe);
            tvIdade = (TextView) view.findViewById(R.id.tv_idade);
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
