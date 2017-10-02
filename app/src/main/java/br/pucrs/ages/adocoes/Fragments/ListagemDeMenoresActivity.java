package br.pucrs.ages.adocoes.Fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import br.pucrs.ages.adocoes.Model.Menor;
import br.pucrs.ages.adocoes.R;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListagemDeMenoresActivity extends AppCompatActivity {

    private boolean isListagemVertical = true;
    private MenuItem buttonTrocaListagem;
    private List<Menor> menores;
    private ListaMenoresVerticalFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_de_menores);

        fragment = new ListaMenoresVerticalFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.listagem_de_menores_layout, fragment)
                .commit();

        RestUtil.getMenoresEndPoint().menores("Bearer anonymous").enqueue(new Callback<List<Menor>>() {
            @Override
            public void onResponse(Call<List<Menor>> call, Response<List<Menor>> response) {
                menores = response.body();
                System.out.println(menores);
                fragment.setItems( isListagemVertical);
            }

            @Override
            public void onFailure(Call<List<Menor>> call, Throwable t) {
                Log.e("ListagemDeMenores", t.getLocalizedMessage(), t);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_de_menores_menu, menu);
        buttonTrocaListagem = (MenuItem) menu.findItem(R.id.troca_listagem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == R.id.troca_listagem) {
            isListagemVertical = !isListagemVertical;
            if (isListagemVertical) {
//                isListagemVertical = true;
//                if (menores != null){
//                    fragment.setItems(menores, isListagemVertical);
//                }
//            } else {
//                isListagemVertical = false;
//                if (menores != null){
//                    fragment.setItems(menores, isListagemVertical);
//                }
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
}
