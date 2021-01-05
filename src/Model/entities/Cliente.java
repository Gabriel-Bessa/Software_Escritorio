package Model.entities;

import java.util.List;

public class Cliente {

    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private List<Processo> processos;

    public Cliente() {
    }

    public Cliente(int id, String nome, String telefone, String endereco, List<Processo> processos) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.processos = processos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(List<Processo> processos) {
        this.processos = processos;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +", ID: " + id + ", Telefone: " + telefone + ", Endereco: " + endereco + ", Processos: " + processos.size();
    }
    
    
    
}
