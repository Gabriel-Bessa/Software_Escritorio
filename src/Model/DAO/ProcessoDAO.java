package Model.DAO;

import Model.entities.Processo;
import java.util.List;

public interface ProcessoDAO {
    
    // faz a inserção de um processo na base de dados
    void inserirProcesso(Processo p);
    
    // Faz a exclusão de um processo da base de dados
    void deletarProcesso(Processo p);
    
    // faz a atualização de um processo da base de dados
    void atualizarProcesso(Processo p);
    
    // Retorna um processo da base de dados baseados em seu numero
    Processo findByNum(int num);
    
    // Retorna uma lista de processos 
    List<Processo> findById(int ID);
    
    // Retorna todos os processos de um determinados cliente
    List<Processo> findByClienteId(int ID);
    
    // Retorna todos os processos
    List<Processo> findAll();
}
