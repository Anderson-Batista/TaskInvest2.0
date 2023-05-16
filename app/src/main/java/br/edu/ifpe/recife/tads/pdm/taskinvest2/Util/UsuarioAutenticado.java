package br.edu.ifpe.recife.tads.pdm.taskinvest2.Util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UsuarioAutenticado {

    public static FirebaseUser usuarioLogado() {
        FirebaseAuth usuario = ConfiguraBD.firebaseAutenticacao();
        return usuario.getCurrentUser();
    }
}
