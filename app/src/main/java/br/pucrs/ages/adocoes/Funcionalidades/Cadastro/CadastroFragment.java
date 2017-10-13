package br.pucrs.ages.adocoes.Funcionalidades.Cadastro;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.pucrs.ages.adocoes.Model.Usuario;
import br.pucrs.ages.adocoes.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 15280414 on 10/06/2017.
 */


public class CadastroFragment extends Fragment {

    private FirstRecyclerAdapter mListAdapter;
    private ProgressBar mProgressBar;

    public CadastroFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastro, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button bCadastrar;
        final EditText etNome;
        final EditText etEmail;
        final EditText etCpf;

        bCadastrar = (Button) view.findViewById(R.id.btnCadastrar);
        etNome   = (EditText) view.findViewById(R.id.txtNome);
        etEmail   = (EditText) view.findViewById(R.id.txtEmail);
        etCpf   = (EditText) view.findViewById(R.id.txtCpf);
        final Usuario usuario = new Usuario();

        bCadastrar.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        usuario.setNome(etNome.getText().toString().trim()) ;
//                        usuario.setCpf(etCpf.getText().toString().trim());
                        usuario.setEmail(etEmail.getText().toString().trim());
                    }
                });

        Call<Usuario> call = br.pucrs.ages.adocoes.Rest.RestUtil.getUsuariosEndPoint().updateUsuario("token", usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<br.pucrs.ages.adocoes.Model.Usuario> call, Response<Usuario> response) {
                String email = response.body().getEmail();
                //send email
            }

            @Override
            public void onFailure(Call<br.pucrs.ages.adocoes.Model.Usuario> call, Throwable t) {
//                Context context = getApplicationContext();
                CharSequence text = t.getLocalizedMessage();
                int duration = Toast.LENGTH_SHORT;

//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
            }
        });

    }
}
