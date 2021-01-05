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

    private Connection con;

    public Area_DAO_JDBC(Connection con) {
        this.con = con;
    }

    @Override
    public void inserir(Area a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Area a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Area a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Area> findAll() {
        PreparedStatement st = null;
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
