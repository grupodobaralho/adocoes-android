package br.pucrs.ages.adocoes.Funcionalidades.ListagemConteudoInstitucional;

import android.app.Activity;
import android.graphics.drawable.Drawable;
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

    public interface OnSelectedListener {
        void onItemSelected(Conteudo conteudo);
    }

    private Activity activity;
    private ArrayList<Conteudo> items;
    private OnSelectedListener listener;

    public void setListener(OnSelectedListener listener) {
        this.listener = listener;
    }

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
        items.add(new Conteudo("Convênio permite criação de aplicativo que facilita adoção de crianças no RS", "Nesta terça-feira (19), às 16h, Poder Judiciário do Rio Grande do Sul, PUCRS e Ministério Público Estadual assinarão convênio para o desenvolvimento de um aplicativo de celular que possibilitará que os candidatos habilitados à adoção tenham acesso às informações de crianças e adolescentes aptos à adoção no Estado. A cerimônia será realizada no gabinete do Presidente do Tribunal de Justiça do RS, Desembargador Luiz Felipe Silveira Difini, no 13° andar do prédio do Corte (Av. Borges de Medeiros, 1565, Porto Alegre).\n" +
                "\n" +
                "O aplicativo foi inicialmente projetado pelos professores da Apple Developer Academy da PUCRS e está sendo desenvolvido por alunos da Faculdade de Informática, sob a orientação de professores, vinculados à Agência Experimental de Engenharia de Software.", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
        items.add(new Conteudo("Aplicativo será desenvolvido para facilitar adoções no Rio Grande do Sul",
                "Um aplicativo para aproximar futuros pais dos futuros filhos e contribuir para a redução da fila de adoções no Estado. Essa é a meta do convênio firmado, nesta terça-feira (19), entre o Tribunal de Justiça do Rio Grande do Sul (TJRS), a Pontifícia Universidade Católica do Rio Grande do Sul (PUCRS) e o Ministério Público Estadual.\n" +
                        "A plataforma, que já deve ter um protótipo em 2018, vai reunir informações das crianças e adolescentes aptos à adoção, como fotos, características e até mesmo suas expectativas. Atualmente, só é possível ter acesso a dados como nome, idade, sexo, raça, condições de saúde e situação jurídica por meio de uma planilha no site do TJRS. De acordo com o Judiciário, são 602 crianças e adolescentes na espera por uma família. Mas a fila de quem está disposto a adotar é nove vezes maior: são 5.461 interessados. O problema é que, muitas vezes, a criança não se enquadra no perfil buscado.\n" +
                        "Quem mais demora a conseguir uma família são maiores de 10 anos, aquelas que sofrem de algum problema de saúde e quem têm irmãos na mesma situação. O aplicativo vem, justamente, com a intenção de flexibilizar o perfil desejado para adoção.\n" +
                        "Conforme o TJRS, as informações e imagens estarão armazenadas em uma área de acesso restrito, cujo conteúdo estará disponível apenas às pessoas habilitadas à adoção, mediante cadastro e solicitação de acesso, que será fornecido pelo Poder Judiciário. Haverá ainda um espaço destinado ao público em geral, com dados básicos e sem identificação das crianças e adolescentes cadastradas no aplicativo, com informações envolvendo a infância e juventude em geral.", new ArrayList<String>(), new Date(), new Date(), new Date(), true));
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
        itemView.tvConteudo.setText(items.get(position).getConteudo());
        itemView.tvTitulo.setText(items.get(position).getTitulo());
        if (position == 0) {
            Drawable drawable = activity.getResources().getDrawable(R.drawable.imagemconteudo1, null);
            itemView.ivConteudo.setImageDrawable(drawable);
        } else {
            Drawable drawable = activity.getResources().getDrawable(R.drawable.imagemconteudo1, null);
            itemView.ivConteudo.setImageDrawable(drawable);
        }

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
            ivConteudo = (ImageView) view.findViewById(R.id.iv_conteudo);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Conteudo conteudo = items.get(getAdapterPosition());
                    if (listener != null) {
                        listener.onItemSelected(conteudo);
                    }
                }
            });
        }
    }



}





