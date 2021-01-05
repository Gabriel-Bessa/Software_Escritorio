package Model.DAO.impl;

import Model.DAO.ProcessoDAO;
import Model.entities.Cliente;
import Model.entities.Processo;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Processo_DAO_JDBC implements ProcessoDAO{

    private Connection con;

    public Processo_DAO_JDBC(Connection con) {
        this.con = con;
    }

    @Override
    public void inserirProcesso(Processo obj) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("INSERT INTO processos "
                    + "(numero_processo, cliente_id, area) "
                    + "VALUES "
                    + "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNum());
            st.setInt(2, obj.getCliente().getId());
            st.setString(3, obj.getCausa());

            int rows = st.executeUpdate();

            if (rows > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new db.dbException("Erro inesperado nenhuma linha afetada!");
            }

        } catch (SQLException sql) {
            throw new db.dbException(sql.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletarProcesso(Processo p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizarProcesso(Processo p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Processo> findByProcesso(Processo processo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Processo> findById(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Processo> findAll() {
        List<Processo> list = new ArrayList<>();
        
        list.add(new Processo(1, "797977979", "Civil", "Gabriel", new Cliente(101, "Gabriel", "(31) 99765-8797", "Rua Joaquim", new ArrayList<>())));
        list.add(new Processo(2, "797312313", "Penal", "Alvin", new Cliente(102, "Alvin", "(31) 99765-8797", "Rua Joaquim", new ArrayList<>())));
        list.add(new Processo(1, "213155656", "Trabalhista", "Lucas", new Cliente(103, "Lucas", "(31) 99765-8797", "Rua Joaquim", new ArrayList<>())));
        
        
        return list;
    }

    @Override
    public Processo findByNum(int num) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
