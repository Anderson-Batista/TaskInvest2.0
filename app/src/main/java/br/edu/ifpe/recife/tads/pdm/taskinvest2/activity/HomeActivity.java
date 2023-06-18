package br.edu.ifpe.recife.tads.pdm.taskinvest2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import br.edu.ifpe.recife.tads.pdm.taskinvest2.R;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.Util.ConfiguraBD;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.fragment.MercadoCotacoesFragment;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.fragment.MinhasTarefasFragment;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private BottomNavigationView bottomNavigationView;
    private MinhasTarefasFragment minhasTarefasFragment;
    private MercadoCotacoesFragment mercadoCotacoesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        minhasTarefasFragment = new MinhasTarefasFragment();
        mercadoCotacoesFragment = new MercadoCotacoesFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.home_container, minhasTarefasFragment)
                .commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.minhas_tarefas:
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.home_container, minhasTarefasFragment)
                            .commit();
                    return true;
                case R.id.mercado_cotacoes:
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.home_container, mercadoCotacoesFragment)
                            .commit();
                    return true;
                default:
                    return false;
            }
        });

        auth = ConfiguraBD.firebaseAutenticacao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcao_sair:
                try {
                    auth.signOut();
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
