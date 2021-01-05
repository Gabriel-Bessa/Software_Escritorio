package Model.entities;

public class Processo {
    
    private int id;
    private String num;
    private String causa;
    private String nomecliente;
    private Cliente cliente;

    public Processo(int id, String num, String causa, String nomecliente, Cliente cliente) {
        this.id = id;
        this.num = num;
        this.causa = causa;
        this.nomecliente = nomecliente;
        this.cliente = cliente;
    }

    public Processo() {
    }
    
    public String getNomeCliente() {
        return nomecliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomecliente = nomeCliente;
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    @Override
    public String toString() {
        return "Processo| id: " + id + ", num: " + num + ", Área: " + causa + ", nomeCliente: ";
    }
}
