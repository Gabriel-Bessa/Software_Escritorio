package Model.DAO.impl;

import Model.DAO.ClienteDAO;
import Model.entities.Cliente;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Cliente_DAO_JDBC implements ClienteDAO{

    private Connection con;

    public Cliente_DAO_JDBC(Connection con) {
        this.con = con;
    }        

    @Override
    public void inserir(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void apagar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente findByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente findByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<Cliente> findByPseudoNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> findAll() {
        
        List<Cliente> list = new ArrayList<>();
        
        list.add(new Cliente(1, "gabriel", "123456", "Rua abc", null));
        list.add(new Cliente(2, "Alvin", "123456", "Rua abc", null));
        list.add(new Cliente(3, "Lucas", "123456", "Rua abc", null));
        list.add(new Cliente(4, "Matheus", "123456", "Rua abc", null));
        
        return list;
    }
    
    
    
}
