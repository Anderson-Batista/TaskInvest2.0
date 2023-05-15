package br.edu.ifpe.recife.tads.pdm.taskinvest2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.edu.ifpe.recife.tads.pdm.taskinvest2.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tv = findViewById(R.id.cadastroMensagem);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar(view);
            }
        });
    }

    public void cadastrar(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }
}