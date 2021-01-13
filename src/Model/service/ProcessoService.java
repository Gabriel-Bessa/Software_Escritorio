package Model.service;

import Model.DAO.Fabrica_dao;
import Model.DAO.ProcessoDAO;
import Model.DAO.impl.Processo_DAO_JDBC;
import Model.entities.Processo;
import java.util.List;

public class ProcessoService {

    private Processo_DAO_JDBC dao = Fabrica_dao.createProcessoDAO();

    public List<Processo> findAll() {
        return dao.findAll();
    }

    public List<Processo> findByClientId(int id) {
        return dao.findByClienteId(id);
    }
    
    public Processo findByNum(String num){
        return dao.findByNum(num);
    }

    public void insert(Processo p) {
        dao.inserirProcesso(p);
    }
    
    public void Deletar(Processo p) {
        dao.deletarProcesso(p);
    }
}
