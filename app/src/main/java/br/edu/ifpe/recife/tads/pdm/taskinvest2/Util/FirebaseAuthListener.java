package br.edu.ifpe.recife.tads.pdm.taskinvest2.Util;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.edu.ifpe.recife.tads.pdm.taskinvest2.activity.HomeActivity;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.activity.LoginActivity;

public class FirebaseAuthListener implements FirebaseAuth.AuthStateListener {

    private final Activity activity;

    public FirebaseAuthListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Intent intent = null;

        if (user != null && !(activity instanceof HomeActivity)) {
            intent = new Intent(activity, HomeActivity.class);
        }
        if (user == null && (activity instanceof HomeActivity)) {
            intent = new Intent(activity, LoginActivity.class);
        }

        if (intent != null) {
            activity.startActivity(intent);
            activity.finish();
        }
    }
}
