package Model.service;

import Model.DAO.Fabrica_dao;
import Model.DAO.ProcessoDAO;
import Model.entities.Processo;
import java.util.List;

public class ProcessoService {
    
     private ProcessoDAO dao = Fabrica_dao.createProcessoDAO();
    
    public List<Processo> findAll(){
        return dao.findAll();
    }
}
