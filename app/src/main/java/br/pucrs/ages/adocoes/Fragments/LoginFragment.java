package br.pucrs.ages.adocoes.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

import br.pucrs.ages.adocoes.MainActivity;
import br.pucrs.ages.adocoes.Model.dto.AccessToken;
import br.pucrs.ages.adocoes.Model.dto.Request.AuthRequest;
import br.pucrs.ages.adocoes.Model.dto.Response.AuthResponse;
import br.pucrs.ages.adocoes.R;
import br.pucrs.ages.adocoes.Rest.RestUtil;
import br.pucrs.ages.adocoes.UserBusiness;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    public LoginFragment() {
    }

    private Button entrarComCadastroButton;
    private Button entrarSemCadastroButton;
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
        entrarComCadastroButton = (Button) view.findViewById(R.id.entrar_com_cadastro_button);
        entrarSemCadastroButton = (Button) view.findViewById(R.id.entrar_sem_cadastro_button);
        cadatrarButton = (Button) view.findViewById(R.id.cadastrar_button);
        esqueceuSenhaTextView = (TextView) view.findViewById(R.id.esqueceu_senha);


        entrarComCadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLoginComCadastro();
            }
        });

        esqueceuSenhaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doForgotPassword();
            }
        });

        entrarSemCadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLoginSemCadastro();
            }
        });
    }

    /**
     * Login de usuário já previamente cadastrado pelo TJ
     */
    private void doLoginComCadastro() {
        try {

            //TODO métodos para validar os campos
            String email = emailEditText.getText().toString();
            String senha = senhaEditText.getText().toString();

            AuthRequest authRquest = new AuthRequest(email, senha);

            String APP_KEY = "adocoes.app";
            String APP_SECRET = "407a4d80fce791751cd83ab1af3d9b26";

            String str = APP_KEY + ":" + APP_SECRET;

            byte[] data = str.getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.NO_WRAP | Base64.URL_SAFE);
            String authorizationString = "Basic " + base64;
            Call<AuthResponse> call = RestUtil.getAuthEndPoint().authUser(authorizationString, authRquest);

            call.enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    AccessToken accessToken = response.body().getAccess_token();
                    UserBusiness.getInstance().updateAccessToken(accessToken.getValue(), accessToken.getUserId());

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                    //Falha no login do usuário.
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Entrar com permissão de visitante
     */
    private void doLoginSemCadastro() {
        //TODO Chamada da tela do restante do aplicativo com perfil de visitante
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
