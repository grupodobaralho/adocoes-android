package br.pucrs.ages.adocoes.Funcionalidades.CadastroCompleto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.pucrs.ages.adocoes.R;

/**
 * Created by kuquert on 17/06/17.
 */

public class CadastroCompletoActivity extends AppCompatActivity {

//    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
//    private TextView toolbarTitle;
    private FloatingActionButton mFloatingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_completo_activity);

//        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setFillViewport(true);
        tabLayout.setupWithViewPager(viewPager);

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.setBundle(getIntent().getExtras());

        adapter.addFragment(new DadosPessoaisFragment(), "Dados Pessoais");
        adapter.addFragment(new ContatoFragment(), "Contatos");
        adapter.addFragment(new EstadoCivilFragment(), "Estado Civil");
        adapter.addFragment(new ComprovantesFragment(), "Comprovantes");

        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private Bundle bundle;

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        void setBundle(Bundle bundle) {
            this.bundle = bundle;
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = mFragmentList.get(position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
