package br.edu.ifpe.recife.tads.pdm.taskinvest2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import br.edu.ifpe.recife.tads.pdm.taskinvest2.R;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.Util.ConfiguraBD;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    EditText campoEmail;
    EditText campoSenha;
    Button botaoAcessar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        TextView tv = findViewById(R.id.cadastroMensagem);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar(view);
            }
        });

        auth = ConfiguraBD.firebaseAutenticacao();
        inicializarComponentes();
    }

    public void validarAutenticacao(View view) {

        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {

                Usuario usuario = new Usuario();

                usuario.setEmail(email);
                usuario.setSenha(senha);

                logar(usuario);

            } else {
                Toast.makeText(this, "Preencha a senha.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Preencha o email.", Toast.LENGTH_SHORT).show();
        }
    }

    private void logar(Usuario usuario) {

        auth.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    abrirHome();
                } else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Usuário não está cadastrado.";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Email ou senha incorreto.";
                    } catch (Exception e) {
                        excecao = "Erro ao tentar logar com esse usuário  " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void cadastrar(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAuth = auth.getCurrentUser();

        if (usuarioAuth != null) {
            abrirHome();
        }
    }

    private void inicializarComponentes() {
        campoEmail = findViewById(R.id.editTextEmailLogin);
        campoSenha = findViewById(R.id.editTextSenhaLogin);
        botaoAcessar = findViewById(R.id.buttonAcessar);
    }
}