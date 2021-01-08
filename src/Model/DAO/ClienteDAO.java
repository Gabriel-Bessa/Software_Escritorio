package Model.DAO;

import Model.entities.Cliente;
import Model.service.ProcessoService;
import java.util.List;

public interface ClienteDAO {
    
    // inseri no DB cu cliente
    void inserir(Cliente cliente);
    // remove do DB um cliente
    void apagar(Cliente cliente);
    // Atualiza do DB um cliente
    void atualizar(Cliente cliente);
    // Retorna as pessoas com nomes similares ao buscado
    List<Cliente> findByPseudoNome(String nome);
    // retorna um cliente ao buscar um id
    Cliente findByID(int id);
    // retorna um cliente ao buscar um nome
    Cliente findByNome(String nome);
    // retorna todos os clientes 
    List<Cliente> findAll();
    // Retorna apenas o nome do cliente com id buscado
    String findNomeById(int id);

}
