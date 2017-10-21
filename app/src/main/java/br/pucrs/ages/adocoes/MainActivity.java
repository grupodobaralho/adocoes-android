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

import br.pucrs.ages.adocoes.Database.SharedPreferences.UserBusiness;
import br.pucrs.ages.adocoes.Funcionalidades.Favoritos.FavoritosFragment;
import br.pucrs.ages.adocoes.Funcionalidades.ListagemDeMenores.ListaMenoresFragment;
import br.pucrs.ages.adocoes.Funcionalidades.Login.LoginActivity;
import br.pucrs.ages.adocoes.Settings.SettingsActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Variável responsável por receber a referência dos intens de menu.
    private MenuItem mostraTrocaParaHorizontal;
    private MenuItem mostraTrocaParaVertical;
    private  boolean isListaVertical = true;

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
//            case R.id.troca_para_vertical:
//                ListaMenoresFragment myFragment = (ListaMenoresFragment)getSupportFragmentManager().findFragmentByTag("Lista Vertical");
//                if (myFragment != null && myFragment.isVisible()) {
//                    myFragment.setItems(true);
//                }
//                return true;
            case R.id.troca_para_horizontal:
                ListaMenoresFragment myFragment1 = (ListaMenoresFragment)getSupportFragmentManager().findFragmentByTag("Lista de Menores");
                if (myFragment1 != null && myFragment1.isVisible()) {
                    isListaVertical = !isListaVertical;
                    myFragment1.setItems(isListaVertical);
                }
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

        switch (id) {
            case R.id.nav_favoritos:
                displayView(1);
                break;
            case R.id.nav_listagem_vertical:
                displayView(2);
                break;
            case R.id.nav_logout:
                displayView(3);
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayView(int viewId) {

        String title = getString(R.string.app_name);

        Fragment fragment = null;

        //Apaga as opções de troca.
        mostraTrocaParaHorizontal.setVisible(false);
        mostraTrocaParaVertical.setVisible(false);


        switch (viewId) {
            case 1:
                fragment = new FavoritosFragment();
                title = "Lista de Favoritos";
                break;
            case 2:
                fragment = new ListaMenoresFragment();
                title = "Lista de Menores";
                //Mostra a opção de troca
                mostraTrocaParaHorizontal.setVisible(true);
                break;
            case 3:
                finish();
                Intent intent = new Intent(this, LoginActivity.class);
                UserBusiness.getInstance().setAnonymousToken();
                startActivity(intent);
                break;
            default:
                fragment = new FavoritosFragment();
                title = "Lista de Favoritos";
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment, title);
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