package br.pucrs.ages.adocoes.Funcionalidades.ListagemConteudoInstitucional;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import br.pucrs.ages.adocoes.Model.Conteudo;
import br.pucrs.ages.adocoes.R;


/**
 * Created by Bruno Cardoso on 24/06/2017.
 */

public class ConteudoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Activity activity;
    private ArrayList<Conteudo> items;

    public ConteudoRecyclerAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(ArrayList<Conteudo> items) {
        if (items != null) {
            this.items = items;
            notifyDataSetChanged();
        }
    }

    public void setMockData() {
        items = new ArrayList<>();
        items.add(new Conteudo("Pepo ijcsiojacjiaoicjas aisjioasji aosijcioasjcioaj asiojiaijsioa aisojciosajcoia aoijsciojasioj oiasjcoiajsc oasijciojsa oaijscoiasj aiosjcoiajsc asoijciasocjaois oaiscjiosac oaijscoiasj aiosjcoiajsc asoijciasocjaois oaiscjiosa oaijscoiasj aiosjcoiajsc asoijciasocjaois oaiscjiosa         items.add(new Conteudo(\"Pepo ijcsiojacjiaoicjas aisjioasji aosijcioasjcioaj asiojiaijsioa aisojciosajcoia aoijsciojasioj oiasjcoiajsc oasijciojsa oaijscoiasj aiosjcoiajsc asoijciasocjaois oaiscjiosac oaijscoiasj aiosjcoiajsc asoijciasocjaois oaiscjiosa oaijscoiasj aiosjcoiajsc asoijciasocjaois oaiscjiosa \", \"Pepo\", new ArrayList<String>(), new Date(), new Date(), new Date(), true));\n", "Pepo", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
        items.add(new Conteudo("Pepo", "Pepo", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
        items.add(new Conteudo("Pepo", "Pepo", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
        items.add(new Conteudo("Pepo", "Pepo", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
        items.add(new Conteudo("Pepo", "Pepo", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
        items.add(new Conteudo("Pepo", "Pepo", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
        items.add(new Conteudo("Pepo", "Pepo", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
        items.add(new Conteudo("Pepo", "Pepo", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
        items.add(new Conteudo("Pepo", "Pepo", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
        items.add(new Conteudo("Pepo", "Pepo", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
        setData(items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conteudo_institucional_item, parent, false);
        return new ConteudoItemView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ConteudoItemView itemView = (ConteudoItemView) holder;
        itemView.tvConteudo.setText(items.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ConteudoItemView extends RecyclerView.ViewHolder {

        TextView tvConteudo;
        TextView tvTitulo;
        ImageView ivConteudo;

        ConteudoItemView(View view) {
            super(view);
            tvConteudo = (TextView) view.findViewById(R.id.tv_conteudo);
            tvTitulo = (TextView) view.findViewById(R.id.tv_titulo);
            ivConteudo = (ImageView) view.findViewById(R.id.image_view);
        }
    }



}





