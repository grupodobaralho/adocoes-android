package br.pucrs.ages.adocoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.pucrs.ages.adocoes.Fragments.FirstFragment;
import br.pucrs.ages.adocoes.Fragments.ListaMenoresCardFragment;
import br.pucrs.ages.adocoes.Fragments.ListaMenoresVerticalFragment;
import br.pucrs.ages.adocoes.Fragments.LoginFragment;
import br.pucrs.ages.adocoes.Fragments.SecondFragment;
import br.pucrs.ages.adocoes.Fragments.TermosFragment;
import br.pucrs.ages.adocoes.Settings.SettingsActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Variável responsável por receber a referência dos intens de menu.
    private MenuItem mostraTrocaParaHorizontal;
    private MenuItem mostraTrocaParaVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //Instanciação das referências.
        mostraTrocaParaHorizontal = menu.findItem(R.id.troca_para_horizontal);
        mostraTrocaParaVertical = menu.findItem(R.id.troca_para_vertical);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //Faz as transações
        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.troca_para_vertical:
                displayView(9);
                return true;

            case R.id.troca_para_horizontal:
                displayView(8);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lista) {
            displayView(0);
        } else if (id == R.id.nav_favoritos) {
            displayView(1);
        } else if (id == R.id.nav_institucional) {
            displayView(2);
        } else if (id == R.id.nav_termos) {
            displayView(2);
        } else if (id == R.id.nav_atualizar_cadastro) {
            displayView(4);
        } else if (id == R.id.nav_preferencias) {
            displayView(5);
        } else if (id == R.id.nav_sair) {
            displayView(6);
        } else if (id == R.id.nav_tela_login) {
            displayView(7);
        } else if (id == R.id.nav_listagem_horizontal) {
            displayView(8);
        } else if (id == R.id.nav_listagem_vertical) {
            displayView(9);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayView(int viewId) {

        String title = getString(R.string.app_name);

        Fragment fragment;

        //Apaga as opções de troca.
        mostraTrocaParaHorizontal.setVisible(false);
        mostraTrocaParaVertical.setVisible(false);


        switch (viewId) {
            case 0:
                fragment = new FirstFragment();
                title  = "Crianças e Adolescentes";
                break;
            case 1:
                fragment = new SecondFragment();
                title = "Favoritados";
                break;
            case 2:
                fragment = new TermosFragment();
                title = "Termos de uso";
                break;
            case 7:
                fragment = new LoginFragment();
                title = "Login teste";
                break;
            case 8:
                fragment = new ListaMenoresCardFragment();
                title = "Lista Horizontal";
                //Mostra a opção de troca
                mostraTrocaParaVertical.setVisible(true);
                break;
            case 9:
                fragment = new ListaMenoresVerticalFragment();
                title = "Lista Vertical";
                //Mostra a opção de troca
                mostraTrocaParaHorizontal.setVisible(true);
                break;
            default:
                fragment = new FirstFragment();
                title = "Adoções app";
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }
}