package br.edu.ifpe.recife.tads.pdm.taskinvest2.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.recife.tads.pdm.taskinvest2.R;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.adapter.TarefaRecyclerViewAdapter;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.model.Tarefa;

public class MinhasTarefasFragment extends Fragment {

    private RecyclerView tarefasRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_minhas_tarefas, container, false);

        tarefasRecyclerView = view.findViewById(R.id.tasks_recycler_view);
        tarefasRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        List<Tarefa> tarefas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            tarefas.add(new Tarefa(i + " Lorem ipsum dolor sit amet, consectetur adipiscing elit.", null, null));
        }

        TarefaRecyclerViewAdapter tarefaRecyclerViewAdapter = new TarefaRecyclerViewAdapter(tarefas);
        tarefasRecyclerView.setAdapter(tarefaRecyclerViewAdapter);

        return view;
    }
}
