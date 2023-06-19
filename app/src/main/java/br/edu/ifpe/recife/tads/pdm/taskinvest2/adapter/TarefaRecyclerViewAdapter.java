package br.edu.ifpe.recife.tads.pdm.taskinvest2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifpe.recife.tads.pdm.taskinvest2.R;
import br.edu.ifpe.recife.tads.pdm.taskinvest2.model.Tarefa;

public class TarefaRecyclerViewAdapter extends RecyclerView.Adapter<TarefaHolder> {

    private List<Tarefa> tarefas;

    public TarefaRecyclerViewAdapter(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public TarefaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_tarefa, parent, false);
        return new TarefaHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TarefaHolder holder, int position) {
        Tarefa item = tarefas.get(position);
        CheckBox tarefa = holder.getTarefa();
        tarefa.setText(item.getTitulo());
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }
}
