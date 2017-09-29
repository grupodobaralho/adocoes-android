package br.pucrs.ages.adocoes.MenorDetails;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;

/**
 * Created by Matheus on 03/09/2017.
 */

public class ParentesListFragment extends Fragment {

    private static final String ARGUMENT_MENORES_VINCULADOS = "menoresVinculados";

    private ArrayList<Menor> mMenores;

    public static ParentesListFragment newInstance() { return new ParentesListFragment(); }

    // TODO: Criar versão do método abaixo que use um Bundle para setar mMenores no fragment

    public static ParentesListFragment newInstance(Menor menor) {
        final Bundle args = new Bundle();
        args.putSerializable(ARGUMENT_MENORES_VINCULADOS, (Serializable) menor.getMenoresVinculados());
        final ParentesListFragment fragment = new ParentesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Fazer as paradas do delegate

        //
        final Bundle args = getArguments();
//        mMenores = (ArrayList<Menor>) args.getSerializable(ARGUMENT_MENORES_VINCULADOS);
        mMenores = new ArrayList();
}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_parentes_list, container, false);

        final Activity activity = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayout = new LinearLayoutManager(activity);
        linearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(new ParentesAdapter(activity));
        return view;
    }

    class ParentesAdapter extends RecyclerView.Adapter<ViewHolder> {

        private LayoutInflater mLayoutInflater;

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

            // setar click listener no futuro
        }

        @Override
        public int getItemCount() {
            return mMenores.size();
        }

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
        }

        private void setData(Menor menor) {
            mNomeTextView.setText(menor.getNome());
//            mParentescoTextView.setText(  PEGAR PARENTESCO  );
//            mImageView.setImageResource(  PEGAR IMAGEM  );
        }

    }
}
