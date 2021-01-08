package Model.DAO;

import Model.DAO.impl.Area_DAO_JDBC;
import Model.DAO.impl.Cliente_DAO_JDBC;
import Model.DAO.impl.Processo_DAO_JDBC;
import db.DB;

public class Fabrica_dao {
    
    // Retorna uma instancia da ponte entre DB e aplicação relacionado aos clientes
    public static Cliente_DAO_JDBC createClienteDAO(){
        return new Cliente_DAO_JDBC(DB.getConnection());
    }
    
    // Retorna uma instancia da ponte entre DB e aplicação relacionado aos Processos
    public static Processo_DAO_JDBC createProcessoDAO(){
        return new Processo_DAO_JDBC(DB.getConnection());
    }
    
    // Retorna uma instancia da ponte entre DB e aplicação relacionado aos Areas
    public static Area_DAO_JDBC creAreaDAO(){
        return new Area_DAO_JDBC(DB.getConnection());
    }
    
}
