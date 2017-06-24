package br.pucrs.ages.adocoes.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.Model.Usuario;
import br.pucrs.ages.adocoes.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 15280414 on 16/06/2017.
 */


public class ListaInteresseFragment extends Fragment {

    private FirstRecyclerAdapter mListAdapter;
    private ProgressBar mProgressBar;

    public ListaInteresseFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.portfolio_allocation_list);

        final ArrayList<String> menores = new ArrayList<String>();

        Call<List<Menor>> call;
        call = br.pucrs.ages.adocoes.Rest.RestUtil.getMenoresEndPoint().menores("token"); //GET /interessados/{id_interessado}/menores

        call.enqueue(new Callback<List<Menor>>() {
            @Override
            public void onResponse(Call<List<br.pucrs.ages.adocoes.Model.Menor>> call, Response<List<Menor>> response) {
                for (br.pucrs.ages.adocoes.Model.Menor menor : response.body()) {
                    menores.add(menor.getId());
                }
            }

            @Override
            public void onFailure(Call<List<br.pucrs.ages.adocoes.Model.Menor>> call, Throwable t) {
//              Context context = getApplicationContext();
                CharSequence text = t.getLocalizedMessage();
                int duration = Toast.LENGTH_SHORT;
            }
        });

        recyclerView.setAdapter(new FirstRecyclerAdapter(this.getActivity())); //TODO como passar os dados
    }
}
