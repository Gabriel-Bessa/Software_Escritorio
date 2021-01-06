package Model.DAO.impl;

import Model.DAO.ClienteDAO;
import Model.entities.Cliente;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente_DAO_JDBC implements ClienteDAO {

    private Connection con;

    public Cliente_DAO_JDBC(Connection con) {
        this.con = con;
    }

    @Override
    public void inserir(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void apagar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente findByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente findByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> findByPseudoNome(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("SELECT * FROM escritorio_db.cliente "
                    + "where nome LIKE ?;");
            st.setString(1, "%" + nome + "%");

            rs = st.executeQuery();
            List<Cliente> list = new ArrayList<>();

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("cliente_id"),                        
                        rs.getString("nome"),
                        rs.getString("endereco"),                        
                        rs.getString("telefone"),
                        null);

                list.add(cliente);
            }

            return list;
        } catch (SQLException e) {
            throw new db.dbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }

    @Override
    public List<Cliente> findAll() {

        List<Cliente> list = new ArrayList<>();

        list.add(new Cliente(1, "gabriel", "123456", "Rua abc", null));
        list.add(new Cliente(2, "Alvin", "123456", "Rua abc", null));
        list.add(new Cliente(3, "Lucas", "123456", "Rua abc", null));
        list.add(new Cliente(4, "Matheus", "123456", "Rua abc", null));

        return list;
    }

    public Cliente instanciaCliente(ResultSet rs) {
        Cliente cliente = new Cliente();

        try {
            cliente.setId(rs.getInt("id"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setNome(rs.getString("nome"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setProcessos(null);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cliente;
    }

}
