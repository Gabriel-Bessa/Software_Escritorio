package Model.service;

import Model.DAO.ClienteDAO;
import Model.DAO.Fabrica_dao;
import Model.DAO.ProcessoDAO;
import Model.entities.Cliente;
import java.util.List;

public class ClienteService {
    
    private ClienteDAO dao = Fabrica_dao.createClienteDAO(); 
    
    public List<Cliente> findAll(){
        return dao.findAll();
    }
    
    public List<Cliente> findByPseudo(String nome){
        return dao.findByPseudoNome(nome);
    }
}
