package Model.service;

import Model.DAO.Fabrica_dao;
import Model.DAO.impl.Cliente_DAO_JDBC;
import Model.entities.Cliente;
import java.util.List;

public class ClienteService {
    
    private Cliente_DAO_JDBC dao = Fabrica_dao.createClienteDAO(); 

    public List<Cliente> findAll(){
        return dao.findAll();
    }
    
    public Cliente findByNome(String nome){
        return dao.findByNome(nome);
    }
    
    public List<Cliente> findByPseudo(String nome){
        return dao.findByPseudoNome(nome);
    }
    
    public String findNomeById(int ID){
        return dao.findNomeById(ID);
    }
    
    public Cliente findByClienteById(int id){
        return dao.findByID(id);
    }
    
    public void setService(ProcessoService service) {
         dao.setService(service);
    }

    
    
}
