package br.pucrs.ages.adocoes.Funcionalidades.CadastroCompleto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.pucrs.ages.adocoes.R;

/**
 * Created by kuquert on 17/06/17.
 */

public class EstadoCivilFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_estado_civil, container, false);
    }
}
