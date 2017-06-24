package br.pucrs.ages.adocoes.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment implements FirstRecyclerAdapter.OnMenorSelectedListener {

    private FirstRecyclerAdapter mListAdapter;
    private ProgressBar mProgressBar;
    private ArrayList<Pair<String, Integer>> items = new ArrayList<>();

    public FirstFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.portfolio_allocation_list);
        mProgressBar = (ProgressBar) view.findViewById(R.id.native_progress_bar);

        mProgressBar.setVisibility(View.GONE);
        mListAdapter = new FirstRecyclerAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mListAdapter);
        recyclerView.setVisibility(View.VISIBLE);

        Call<List<Menor>> call;
        call = br.pucrs.ages.adocoes.Rest.RestUtil.getMenoresEndPoint().menores("token");

        call.enqueue(new Callback<List<Menor>>() {
            @Override
            public void onResponse(Call<List<br.pucrs.ages.adocoes.Model.Menor>> call, Response<List<Menor>> response) {
                for (br.pucrs.ages.adocoes.Model.Menor menor : response.body()) {
//                    items.add(menor.getNome());
                }
            }

            @Override
            public void onFailure(Call<List<br.pucrs.ages.adocoes.Model.Menor>> call, Throwable t) {
//                Context context = getApplicationContext();
                CharSequence text = t.getLocalizedMessage();
                int duration = Toast.LENGTH_SHORT;
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
            }
        });



        items.add(new Pair("Marcus Kuquert", R.drawable.boy));
        items.add(new Pair("André Botelho", R.drawable.boy_1));
        items.add(new Pair("Gabriel Machado", R.drawable.boy_2));
        items.add(new Pair("Eduardo Arruda", R.drawable.boy_3));

        items.add(new Pair("Cassio Trindade", R.drawable.girl));
        items.add(new Pair("André Botelho", R.drawable.girl_1));
        items.add(new Pair("Gabriel Machado", R.drawable.girl_2));
        items.add(new Pair("Eduardo Arruda", R.drawable.girl_3));

        mListAdapter.setData(items);
        mListAdapter.setListener(this);
    }


    @Override
    public void OnMenorItemSelected(Object menor) {
        MenorDetailFragment fragment = new MenorDetailFragment();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(FirstFragment.class.getName())
                .replace(R.id.content_frame, fragment, MenorDetailFragment.class.getSimpleName())
                .commit();
    }
}
