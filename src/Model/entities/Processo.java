package Model.entities;

public class Processo {
    
    private int id;
    private String num;
    private String causa;
    private String nomecliente;
    private int id_cliente;

    public Processo(int id, String num, String causa, String nomecliente, int id_cliente) {
        this.id = id;
        this.num = num;
        this.causa = causa;
        this.nomecliente = nomecliente;
        this.id_cliente = id_cliente;
    }

    public Processo(String num, String causa, String nomecliente, int id_cliente) {
        this.num = num;
        this.causa = causa;
        this.nomecliente = nomecliente;
        this.id_cliente = id_cliente;
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

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


    @Override
    public String toString() {
        return "Processo| id: " + id + ", num: " + num + ", Área: " + causa + ", nomeCliente: " + nomecliente;
    }
}
