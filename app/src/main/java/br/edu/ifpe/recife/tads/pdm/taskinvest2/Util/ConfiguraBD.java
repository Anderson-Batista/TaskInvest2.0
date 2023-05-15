package br.edu.ifpe.recife.tads.pdm.taskinvest2.Util;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguraBD {

    private static FirebaseAuth auth;

    //verifica se existe instacia do firabese não é nula
    public static FirebaseAuth firebaseAutenticacao() {
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
