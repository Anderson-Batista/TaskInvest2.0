package br.edu.ifpe.recife.tads.pdm.taskinvest2.adapter;

import android.view.View;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifpe.recife.tads.pdm.taskinvest2.R;

public class TarefaHolder extends RecyclerView.ViewHolder {
    private CheckBox tarefa;

    public TarefaHolder(View itemView) {
        super(itemView);
        tarefa = itemView.findViewById(R.id.task_checkbox);
    }

    public CheckBox getTarefa() {
        return tarefa;
    }
}
