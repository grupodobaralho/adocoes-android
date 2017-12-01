package br.pucrs.ages.adocoes.Funcionalidades.ListagemDeMenores;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
 * Created by kuquert on 29/05/17.
 */

public class ListaHorizontalAdapter extends RecyclerView.Adapter<ListaHorizontalAdapter.MenorItemView>{

    private OnMenorSelectedListener mOnMenorSelectedListener;
    private OnMenorSelectedListener mOnMenorFavoritarListener;
    private List<MenorInteresse> listMenorInteresse;

    public void setListener(OnMenorSelectedListener listener) {
        this.mOnMenorSelectedListener = listener;
    }

    public void setOnMenorFavoritarListener(OnMenorSelectedListener onMenorFavoritarListener) {
        mOnMenorFavoritarListener = onMenorFavoritarListener;
    }

    private Activity activity;
    //Title, Image
    private List<Menor> items = new ArrayList<>();

    public ListaHorizontalAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(List<Menor> items) {
        if (items != null) {
            this.items = items;
            notifyDataSetChanged();
        }
    }

    @Override
    public ListaHorizontalAdapter.MenorItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_horizontal_item, parent, false);
        return new MenorItemView(view);
    }

    @Override
    public void onBindViewHolder(ListaHorizontalAdapter.MenorItemView holder, int position) {
        final MenorItemView itemView = holder;
        final Menor menor = items.get(position);
        final String token = UserBusiness.getInstance().getAccessToken();
        listMenorInteresse = new ArrayList<>();

        if (menor != null) {
            itemView.tvNome.setText(menor.getNome());
            itemView.tvIdade.setText("Idade: "+Integer.toString(menor.getIdade()));
            itemView.tvSexo.setText("Sexo: "+menor.getSexo().toString());

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
