package Model.DAO;

import Model.entities.Area;
import java.util.List;

public interface AreaDAO {
    // inserir uma nova area ao banco de dados
    void inserir(Area a);
    // deletar uma area ao banco de dados
    void deletar(Area a);
    // atualizar uma area ao banco de dados
    void atualizar(Area a);
    // Buscar todas as areas do db
    List<Area> findAll();
}
