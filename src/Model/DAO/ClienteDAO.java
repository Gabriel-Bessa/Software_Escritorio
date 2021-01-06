package Model.DAO;

import Model.entities.Cliente;
import java.util.List;

public interface ClienteDAO {
    
    void inserir(Cliente cliente);
    void apagar(Cliente cliente);
    void atualizar(Cliente cliente);
    List<Cliente> findByPseudoNome(String nome);
    Cliente findByID(int id);
    Cliente findByNome(String nome);
    List<Cliente> findAll();
}
