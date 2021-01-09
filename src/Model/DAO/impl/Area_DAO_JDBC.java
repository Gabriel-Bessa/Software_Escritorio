package Model.DAO.impl;

import Model.DAO.AreaDAO;
import Model.entities.Area;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Area_DAO_JDBC implements AreaDAO {
    // Implementa os metodos da classe abstrata AreaDAO
    
    // Variavel para armazenar a conexão com o DB
    private Connection con;

    // Construtor com a injeção da conexão com o DB
    public Area_DAO_JDBC(Connection con) {
        this.con = con;
    }
    
    // Método para inserção de uma área no DB
    @Override
    public void inserir(Area a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Método para a exclusão de uma área no DB
    @Override
    public void deletar(Area a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Método para atualizar uma área no DB
    @Override
    public void atualizar(Area a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Método que retorna todas as areas presentes no DB
    @Override
    public List<Area> findAll() {
        // Instacia um objeto PreparedStatment para comportar o SQL
        PreparedStatement st = null;
        // Instancia um objeto ResultSet para receber os resultas da busca
        ResultSet rs = null;
        try {
            st = con.prepareStatement("SELECT * FROM areas");
            

            rs = st.executeQuery();
            List<Area> list = new ArrayList<>();

            while (rs.next()) {
                
                Area dep = instaciarArea(rs);
                
                list.add(dep);
            }
            return list;
        } catch (SQLException e) {
            throw new db.dbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
    private Area instaciarArea(ResultSet rs) throws SQLException {
        Area dp = new Area();
        dp.setNome(rs.getString("nome"));
        dp.setId(rs.getInt("id"));
        return dp;
    }

}
