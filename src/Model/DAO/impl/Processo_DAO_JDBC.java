package Model.DAO.impl;

import Model.DAO.ProcessoDAO;
import Model.entities.Cliente;
import Model.entities.Processo;
import Model.service.ClienteService;
import db.DB;
import static gui.MainViewController.serviceCliente;
import gui.util.Alert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Processo_DAO_JDBC implements ProcessoDAO {

    private Connection con;

    private ClienteService service;


    public Processo_DAO_JDBC(Connection con) {
        this.con = con;
        
        this.service = serviceCliente;
    }

    @Override
    public void inserirProcesso(Processo obj) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("INSERT INTO processos (numero_processo, cliente_id, area) "
                    + "VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNum());
            st.setInt(2, service.findByNome(obj.getNomeCliente()).getId());
            st.setString(3, obj.getCausa());

            int rows = st.executeUpdate();
            if (rows < 0) {
                Alert.showAlert("SQLEXception", "Erro no resgistro", "Linhas afetadas: " + rows, javafx.scene.control.Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            Alert.showAlert("SQLEXception", "Erro no resgistro", e.getMessage(), javafx.scene.control.Alert.AlertType.ERROR);
        }
    }

    @Override
    public void deletarProcesso(Processo p) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("DELETE FROM processos "
                    + "WHERE processos_id = ?");
            st.setInt(1, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizarProcesso(Processo p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Processo> findById(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Processo> findAll() {
        List<Processo> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("SELECT * FROM escritorio_db.processos");
            rs = st.executeQuery();

            while (rs.next()) {
                Processo p = new Processo();

                p.setCausa(rs.getString("area"));
                p.setId(rs.getInt("processos_id"));
                p.setNum(rs.getString("numero_processo"));
                p.setId_cliente(rs.getInt("cliente_id"));
                p.setNomeCliente(service.findNomeById(rs.getInt("cliente_id")));
                Cliente c = service.findByClienteById(p.getId_cliente());

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Processo findByNum(String num) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("SELECT * FROM escritorio_db.processos "
                    + "where numero_processo = ? ;");
            st.setString(1, num);

            rs = st.executeQuery();

            while (rs.next()) {
                Processo p = new Processo();

                p.setCausa(rs.getString("area"));
                p.setId(rs.getInt("processos_id"));
                p.setNum(rs.getString("numero_processo"));
                p.setId_cliente(rs.getInt("cliente_id"));
                p.setNomeCliente(service.findNomeById(p.getId()));

                return p;
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
    public List<Processo> findByClienteId(int ID) {
        List<Processo> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("SELECT * FROM escritorio_db.processos "
                    + "where cliente_id = ? ;");
            st.setInt(1, ID);

            rs = st.executeQuery();

            while (rs.next()) {
                Processo p = new Processo();

                p.setCausa(rs.getString("area"));
                p.setId(rs.getInt("processos_id"));
                p.setNum(rs.getString("numero_processo"));
                p.setId_cliente(rs.getInt("cliente_id"));

                list.add(p);
            }

            return list;
        } catch (SQLException e) {
            throw new db.dbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        
    }

    /*private void removeProcessoDoCliente(Processo p){
        int id = p.getId_cliente();
        Cliente c = service.findByClienteById(id);
        List<Processo> list = c.getProcessos();
        
        for (Processo processo : list) {
            if(p.getNum().equals(processo.getNum())){
                System.out.println(p.getNum());
            }
        }
        
        c.setProcessos(list);
        service.update(c);
    }*/
}
