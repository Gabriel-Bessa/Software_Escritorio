package Model.service;

import Model.DAO.AreaDAO;
import Model.DAO.Fabrica_dao;
import Model.entities.Area;
import java.util.List;

public class AreaService {

    private AreaDAO dao = Fabrica_dao.creAreaDAO();
    
    
    
    public List<Area> findAll(){
        return dao.findAll();
    }
    
}
