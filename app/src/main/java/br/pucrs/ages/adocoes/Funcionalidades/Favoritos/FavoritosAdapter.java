package br.pucrs.ages.adocoes.Funcionalidades.Favoritos;

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
        final FavoritosAdapter.MenorItemView itemView = holder;
        final Menor menor = items.get(position);
        System.out.println("--MENOR--");
        if (menor != null) {
            System.out.println("--MENOR NOT NULL--");
            final String token = UserBusiness.getInstance().getAccessToken();

            RestUtil.getMenoresEndPoint().menor(menor.getId(),token).enqueue(new Callback<Menor>() {
                @Override
                public void onResponse(Call<Menor> call, Response<Menor> response) {



                    Menor m1 = response.body();



                    for (RefMidia midia : m1.getMidias()) {
                        if (midia.isPrincipal()) {
                            RestUtil.getMenoresEndPoint().menorMidia(m1.getId(), midia.getId(), token).enqueue(new Callback<MenorMidia>() {
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
                                    System.out.println("--PROBLEMA NA IMG--");
                                }
                            });
                            break;
                        }
                    }



                }

                @Override
                public void onFailure(Call<Menor> call, Throwable t) {
                    System.out.println("--PROBLEMA NO MENOR--");
                }
            });




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
