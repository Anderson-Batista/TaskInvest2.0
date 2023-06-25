package br.edu.ifpe.recife.tads.pdm.taskinvest2.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import org.jetbrains.annotations.NotNull;

import br.edu.ifpe.recife.tads.pdm.taskinvest2.R;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.Util.ConfiguraBD;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.Util.FirebaseAuthListener;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    Usuario usuario;
    FirebaseAuth auth;
    FirebaseAuthListener authListener;
    EditText campoNome;
    EditText campoEmail;
    EditText campoSenha;
    EditText campoConfirmarSenha;

    Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuthListener(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        TextView loginMensagem = findViewById(R.id.loginMensagem);
        loginMensagem.setOnClickListener(view -> startActivity(new Intent(this, LoginActivity.class)));

        ImageView arrowBack = findViewById(R.id.arrowBack);
        arrowBack.setOnClickListener(view -> startActivity(new Intent(this, LoginActivity.class)));

        inicializar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }

    private void inicializar() {

        campoNome = findViewById(R.id.editTextNome);
        campoEmail = findViewById(R.id.editTextEmail);
        campoSenha = findViewById(R.id.editTextSenha);
        campoConfirmarSenha = findViewById(R.id.editTextConfirmarSenha);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);

    }

    public void validarCampos(View v) {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();
        String senhaConfirmacao = campoConfirmarSenha.getText().toString();

        if (nome.isEmpty()) {
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (senha.isEmpty()) {
            Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show();
            return;
        }
        if (senhaConfirmacao.isEmpty()) {
            Toast.makeText(this, "Confirme a senha", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!senhaConfirmacao.equals(senha)) {
            Toast.makeText(this, "As senhas informadas não correspondem", Toast.LENGTH_SHORT).show();
            return;
        }

        usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        // cadastro
        cadastrarUsuario();
    }

    private void cadastrarUsuario() {
        auth.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NotNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    String excecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "Digete uma senha mais forte.";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Digete um Email válido.";
                    } catch (FirebaseAuthUserCollisionException e) {
                        excecao = "Esta conta já existe.";
                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usuário." + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
