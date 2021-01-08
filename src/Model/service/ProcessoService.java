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

    public void setService(ClienteService service) {
        dao.setService(service);
    }

    public void insert(Processo p) {
        dao.inserirProcesso(p);
    }
}
