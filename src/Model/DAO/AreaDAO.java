package Model.DAO;

import Model.entities.Area;
import java.util.List;

public interface AreaDAO {
    void inserir(Area a);
    void deletar(Area a);
    void atualizar(Area a);
    List<Area> findAll();
}
