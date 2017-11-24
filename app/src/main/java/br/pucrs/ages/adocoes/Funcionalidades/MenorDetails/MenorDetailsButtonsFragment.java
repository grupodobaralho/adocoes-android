package br.pucrs.ages.adocoes.Funcionalidades.MenorDetails;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import br.pucrs.ages.adocoes.Database.SQLite.DatabaseHelper;
import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Model.Body.Interesse;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.pucrs.ages.adocoes.Funcionalidades.MenorDetails.MenorDetailsActivity.EXTRA_MENOR;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenorDetailsButtonsFragment extends Fragment {

    private Menor mMenor;
    private boolean isLogged;


    public MenorDetailsButtonsFragment() {
        // Required empty public constructor
    }

    public static MenorDetailsButtonsFragment newInstance(Menor menor) {

        final Bundle args = new Bundle();

        args.putSerializable(EXTRA_MENOR, menor);

        final MenorDetailsButtonsFragment fragment = new MenorDetailsButtonsFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menor_details_buttons, container, false);

        mMenor = (Menor) getArguments().getSerializable(EXTRA_MENOR);

        isLogged = UserBusiness.getInstance().isLogged();

        ImageButton btnAdotar = (ImageButton) view.findViewById(R.id.btnAdotar);
        ImageButton btnFavoritar = (ImageButton) view.findViewById(R.id.btnFavoritar);

        btnAdotar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Atenção");
                alert.setMessage("Você realmente deseja adotar esta criança / adolescente?");

                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        isLogged = UserBusiness.getInstance().isLogged();
                        // User clicked OK button
                        if (isLogged)
                            demonstrarAdotar(mMenor);
//                            Toast.makeText(getActivity(), "Você não está logado, logo não tens permissão para adotar", Toast.LENGTH_SHORT);
                    }
                });

                alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
//                        Toast.makeText(getActivity(),  "Não" , Toast.LENGTH_SHORT ).show();
                    }
                });

                AlertDialog dialog = alert.create();
                alert.show();
            }
        });


        btnFavoritar.setOnClickListener(new OnClickListener() {
            public void onClick(View v)  {
                isLogged = UserBusiness.getInstance().isLogged();
                if (isLogged)
                    demonstraInteresseApi(mMenor);
                else
                    demonstraInteresseLocal(mMenor);

            }
        });

        return view;
    }

    private void demonstrarAdotar(final Menor menor){
        String token = UserBusiness.getInstance().getAccessToken();
        System.out.println(menor.getId());
        RestUtil.getEuEndPoint().postMenorInteresse(token, new Interesse(menor.getId(), "adotar")).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    Toast.makeText(getActivity(), "Iniciou processo Adoção com " + menor.getNome(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Você já iniciou a Adoção de " + menor.getNome(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Demonstra interesse", t.getLocalizedMessage(), t);
            }
        });
        //Toast.makeText(getActivity(), "Ainda não implementado", Toast.LENGTH_SHORT).show();
    }

    private void demonstraInteresseApi(final Menor menor){
        String token = UserBusiness.getInstance().getAccessToken();
        System.out.println(menor.getId());
        RestUtil.getEuEndPoint().postMenorInteresse(token, new Interesse(menor.getId(), "favoritar")).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    Toast.makeText(getActivity(), "Demonstrou interesse em " + menor.getNome(), Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getActivity(), "Demonstrou interesse em " + menor.getNome(), Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        Log.e("Demonstra interesse", response.errorBody().string());
                        Toast.makeText(getActivity(), "Não foi possível demonstrar interesse em " + menor.getNome(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Demonstra interesse", t.getLocalizedMessage(), t);
                Toast.makeText(getActivity(), "caiu no failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void demonstraInteresseLocal(Menor menor){
        // Chama o Banco e verifica se o menor já é um favorito
        boolean isFavorite = DatabaseHelper.getInstance(getActivity()).contemMenor(menor);
        if(isFavorite) {
            Toast.makeText(getActivity(), "Já é um interessado em " + menor.getNome(), Toast.LENGTH_SHORT).show();
            return;
        }

        // Chama o Banco e tenta inserir um novo favorito
        boolean result = DatabaseHelper.getInstance(getActivity()).insereFavorito(menor);
        if(result)
            Toast.makeText(getActivity(), "Demonstrou interesse em " + menor.getNome(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(), "Não foi possível demonstrar interesse em ", Toast.LENGTH_SHORT).show();
    }

}
