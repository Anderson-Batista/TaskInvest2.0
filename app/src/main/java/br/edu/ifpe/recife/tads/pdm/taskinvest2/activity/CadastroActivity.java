package br.edu.ifpe.recife.tads.pdm.taskinvest2.activity;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import org.jetbrains.annotations.NotNull;

import br.edu.ifpe.recife.tads.pdm.taskinvest2.R;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.Util.ConfiguraBD;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    Usuario usuario;
    FirebaseAuth autenticacao;

    EditText campoNome;
    EditText campoEmail;
    EditText campoSenha;

    Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        TextView tv = findViewById(R.id.loginMensagem);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logar(view);
            }
        });

        inicializar();
    }


    private void inicializar() {

        campoNome = findViewById(R.id.editTextNome);
        campoEmail = findViewById(R.id.editTextEmail);
        campoSenha = findViewById(R.id.editTextSenha);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);

    }

    public void validarCampos(View v) {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!senha.isEmpty()) {

                    usuario = new Usuario();

                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    //cadastro
                    cadastrarUsuario();

                } else {
                    Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show();
        }


    }

    private void cadastrarUsuario() {
        autenticacao = ConfiguraBD.firebaseAutenticacao();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar o usuário.", Toast.LENGTH_SHORT).show();
                } else {
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

    public void logar(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}