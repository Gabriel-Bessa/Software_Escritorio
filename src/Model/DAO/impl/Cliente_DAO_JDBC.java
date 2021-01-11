package Model.DAO.impl;

import Model.DAO.ClienteDAO;
import Model.entities.Cliente;
import Model.entities.Processo;
import Model.service.ProcessoService;
import db.DB;
import static gui.MainViewController.serviceProcesso;
import gui.util.Alert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Cliente_DAO_JDBC implements ClienteDAO {

    private Connection con;

    private ProcessoService service;

    public Cliente_DAO_JDBC(Connection con) {
        this.con = con;
        this.service = serviceProcesso;
    }

    @Override
    public void inserir(Cliente cliente) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("INSERT INTO cliente (nome, telefone, endereco, observacoes) "
                    + "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getTelefone());
            st.setString(3, cliente.getEndereco());
            st.setString(4, cliente.getObservacoes());

            int rows = st.executeUpdate();
            if (rows < 0) {
                Alert.showAlert("SQLEXception", "Erro no resgistro", "Linhas afetadas: " + rows, javafx.scene.control.Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            Alert.showAlert("SQLEXception", "Erro no resgistro", e.getMessage(), javafx.scene.control.Alert.AlertType.ERROR);
        }
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

            System.out.println(rs.next());

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("cliente_id"));
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
        List<Cliente> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("SELECT * FROM cliente");

            rs = st.executeQuery();

            while (rs.next()) {
                Cliente c = instanciaCliente(rs);
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            throw new db.dbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public Cliente instanciaCliente(ResultSet rs) {
        Cliente cliente = new Cliente();

        try {
            cliente.setId(rs.getInt("cliente_id"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setObservacoes(rs.getString("observacoes"));
            cliente.setNome(rs.getString("nome"));
            cliente.setTelefone(rs.getString("telefone"));
            List<Processo> list = serviceProcesso.findByClientId(cliente.getId());
            for (Processo processo : list) {
                processo.setNomeCliente(cliente.getNome());
            }
            cliente.setProcessos(list);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cliente;
    }

    public void setService(ProcessoService service) {
        this.service = service;
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
