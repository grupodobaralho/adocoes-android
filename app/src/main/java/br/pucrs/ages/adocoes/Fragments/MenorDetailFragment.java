package br.pucrs.ages.adocoes.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.pucrs.ages.adocoes.CustomSwipeAdapter;
import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenorDetailFragment extends Fragment {

    private FirstRecyclerAdapter mListAdapter;
    private ProgressBar mProgressBar;
    private ArrayList<String> items = new ArrayList<>();
    ViewPager viewPager;
    CustomSwipeAdapter adapter;

    public MenorDetailFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menor_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.portfolio_allocation_list);
        mProgressBar = (ProgressBar) view.findViewById(R.id.native_progress_bar);

        mProgressBar.setVisibility(View.GONE);
        mListAdapter = new FirstRecyclerAdapter(getActivity());
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        adapter = new CustomSwipeAdapter(getActivity());
        viewPager.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mListAdapter);
        recyclerView.setVisibility(View.VISIBLE);

        Call<List<Menor>> call;
        call = br.pucrs.ages.adocoes.Rest.RestUtil.getMenoresEndPoint().menores("token");

        call.enqueue(new Callback<List<Menor>>() {
            @Override
            public void onResponse(Call<List<Menor>> call, Response<List<Menor>> response) {
                for (Menor menor : response.body()) {
                    items.add(menor.getNome());
                }
            }

            @Override
            public void onFailure(Call<List<Menor>> call, Throwable t) {
//                Context context = getApplicationContext();
                CharSequence text = t.getLocalizedMessage();
                int duration = Toast.LENGTH_SHORT;

//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
            }
        });

        items.add("Marcus Kuquert");
        items.add("André Botelho");
        items.add("Gabriel Machado");
        items.add("Eduardo Arruda");

        mListAdapter.setData(items);
    }
}
