package br.edu.ifpe.recife.tads.pdm.taskinvest2.model;

import java.util.Date;

public class Tarefa {

    private String titulo;
    private String descricao;
    private Date data;

    public Tarefa(String titulo, String descricao, Date data) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getData() {
        return data;
    }
}
