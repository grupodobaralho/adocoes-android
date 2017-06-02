package br.pucrs.ages.adocoes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import br.pucrs.ages.adocoes.R;

public class FirstFragment extends Fragment {

    private FirstRecyclerAdapter mListAdapter;
    private ProgressBar mProgressBar;

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

        ArrayList<String> items = new ArrayList<>();

        items.add("11");
        items.add("11");
        items.add("11");
        items.add("11");
        items.add("11");
        items.add("11");
        items.add("11");
        items.add("11");
        items.add("11");
        items.add("11");
        items.add("11");
        items.add("11");

        mListAdapter.setData(items);
    }
}
