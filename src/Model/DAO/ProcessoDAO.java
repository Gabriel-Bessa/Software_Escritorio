package Model.DAO;

import Model.entities.Processo;
import java.util.List;

public interface ProcessoDAO {
    
    void inserirProcesso(Processo p);
    void deletarProcesso(Processo p);
    void atualizarProcesso(Processo p);
    Processo findByNum(int num);
    List<Processo> findByProcesso(Processo processo);
    List<Processo> findById(int ID);
    List<Processo> findAll();
}
