package br.pucrs.ages.adocoes.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.pucrs.ages.adocoes.Model.Usuario;
import br.pucrs.ages.adocoes.Model.dto.Request.AuthRequest;
import br.pucrs.ages.adocoes.R;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    public LoginFragment() { }
    private Button entrarButton;
    private Button cadatrarButton;
    private EditText emailEditText;
    private EditText senhaEditText;
    private TextView esqueceuSenhaTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailEditText = (EditText) view.findViewById(R.id.email_edit_text);
        senhaEditText = (EditText) view.findViewById(R.id.senha_edit_text);
        entrarButton = (Button) view.findViewById(R.id.entrar_button);
        cadatrarButton = (Button) view.findViewById(R.id.cadastrar_button);
        esqueceuSenhaTextView = (TextView) view.findViewById(R.id.esqueceu_senha);


        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        esqueceuSenhaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doForgotPassword();
            }
        });
    }

    private void doLogin() {
        //TODO métodos para validar os campos
        String email = emailEditText.getText().toString();
        String senha = senhaEditText.getText().toString();

        AuthRequest authRquest = new AuthRequest(email, senha);

        Call<Usuario> call = RestUtil.getAuthEndPoint().authUser(authRquest);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                //Usuário logado.
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                //Falha no login do usuário.
            }
        });
    }

    public void doForgotPassword() {
        final Dialog dialogForgotPassword = new Dialog(this.getActivity());
        dialogForgotPassword.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogForgotPassword.setCancelable(true);
        dialogForgotPassword.setContentView(R.layout.dialog_esqueceu_senha);

        Button recuperarSenhaButton = (Button) dialogForgotPassword.findViewById(R.id.recuperar_senha_button);
        recuperarSenhaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Adicionar chamada backend

                dialogForgotPassword.dismiss();
            }
        });

        dialogForgotPassword.show();
    }
}
