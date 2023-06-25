package br.edu.ifpe.recife.tads.pdm.taskinvest2.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import br.edu.ifpe.recife.tads.pdm.taskinvest2.R;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.Util.ConfiguraBD;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.Util.FirebaseAuthListener;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.fragment.MercadoCotacoesFragment;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.fragment.MinhasTarefasFragment;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private MinhasTarefasFragment minhasTarefasFragment;
    private MercadoCotacoesFragment mercadoCotacoesFragment;
    private FirebaseAuth auth;
    private FirebaseAuthListener authListener;
    private AlertDialog sairDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = ConfiguraBD.firebaseAutenticacao();
        authListener = new FirebaseAuthListener(this);
        sairDialog = makeSairDialog();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcao_sair:
                sairDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private AlertDialog makeSairDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        AlertDialog sairDialog = builder
                .setIcon(R.drawable.baseline_logout_24)
                .setTitle(R.string.logout_dialog_title)
                .setMessage(R.string.logout_dialog_message)
                .setCancelable(false)
                .setPositiveButton(R.string.logout_dialog_positive_button, (dialogInterface, i) -> {
                    try {
                        auth.signOut();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .setNegativeButton(R.string.logout_dialog_negative_button,
                        (dialogInterface, i) -> dialogInterface.cancel())
                .create();

        return sairDialog;
    }
}
