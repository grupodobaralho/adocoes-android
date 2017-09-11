package br.pucrs.ages.adocoes.ListagemDeMenores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.pucrs.ages.adocoes.R;

public class ListagemDeMenoresActivity extends AppCompatActivity {

    private boolean isListagemVertical = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_de_menores);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.listagem_de_menores_layout, new ListaMenoresVerticalFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_de_menores_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == R.id.troca_listagem) {
            isListagemVertical = !isListagemVertical;
            if (isListagemVertical) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.listagem_de_menores_layout, new ListaMenoresVerticalFragment())
                        .commit();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.listagem_de_menores_layout, new ListaMenoresHorizontalFragment())
                        .commit();
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
}
