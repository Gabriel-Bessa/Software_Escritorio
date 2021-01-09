package Model.DAO.impl;

import Model.DAO.ClienteDAO;
import Model.entities.Cliente;
import Model.service.ProcessoService;
import db.DB;
import static gui.ProcessosListaController.serviceProcesso;
import gui.util.Alert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente_DAO_JDBC implements ClienteDAO {

    private Connection con;

    private ProcessoService service = serviceProcesso;

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
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("SELECT * FROM cliente "
                    + "WHERE cliente_id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente(id,
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("endereco"),
                        rs.getString("observacoes"),
                        null);
                return c;
            }

            return null;

        } catch (SQLException e) {
            throw new db.dbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public Cliente findByNome(String nome) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = con.prepareStatement("SELECT * FROM cliente "
                    + "WHERE nome = ?");
            st.setString(1, nome);
            rs = st.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("cliente_id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));
                c.setObservacoes(rs.getString("observacoes"));
                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert.showAlert("SQLExeption", "ERRO na busca", nome + " não foi encontrado!", javafx.scene.control.Alert.AlertType.ERROR);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;
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
                Cliente cliente = instanciaCliente(rs);

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cliente instanciaCliente(ResultSet rs) {
        Cliente cliente = new Cliente();

        try {
            cliente.setId(rs.getInt("cliente_id"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setNome(rs.getString("nome"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setProcessos(null);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cliente;
    }

    public void setService(ProcessoService service) {
        this.service = service;
    }
   
    public static String finNomeByClienteID(int id) {
        return "Gabriel";
    }

    @Override
    public String findNomeById(int id) {

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("SELECT * FROM cliente "
                    + "WHERE cliente_id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();

            while (rs.next()) {
                return rs.getString("nome");
            }

            return "";

        } catch (SQLException e) {
            throw new db.dbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

}
