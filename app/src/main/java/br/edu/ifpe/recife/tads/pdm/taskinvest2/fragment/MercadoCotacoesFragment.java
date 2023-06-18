package br.edu.ifpe.recife.tads.pdm.taskinvest2.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.ifpe.recife.tads.pdm.taskinvest2.R;

public class MercadoCotacoesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mercado_cotacoes, container, false);
    }
}
