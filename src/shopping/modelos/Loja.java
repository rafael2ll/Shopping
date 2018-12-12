/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Loja {

    private String cnpj;
    private String nome;
    private String setorComercial;
    private int setorFisico;
    private SetorFisico setorF;

    public Loja() {
    }

    public Loja(String cnpj, String nome) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Loja(String cnpj, String nome, String setor_comercial, int setor_fisico_id) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.setorComercial = setor_comercial;
        this.setorFisico = setor_fisico_id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public SetorFisico getSetorFisico() {
        return setorF;
    }

    public String getSetorComercial() {
        return setorComercial;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public boolean inserir(Connection conn, String loja_id, String loja_pwd) {
        try {
            //TODO: criar procedimento armazenado para controlar criação de papéis: atribuir acesso às tabelas.
            PreparedStatement role = conn.prepareStatement(
                    "CREATE USER \"" + loja_id.toLowerCase() + "\" WITH ENCRYPTED PASSWORD '" + loja_pwd + "';"
                    + "GRANT \"LojasGroup\" TO \"" + loja_id.toLowerCase() + "\";");
            role.execute();

            PreparedStatement inser = conn.prepareStatement("INSERT INTO loja VALUES(?, ?, ?, ?)");
            inser.setString(1, cnpj);
            inser.setString(2, setorComercial);
            inser.setInt(3, setorFisico);
            inser.setString(4, nome);
            inser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean remover(Connection conn, String loja_id, String loja_cnpj) {
        try {
            PreparedStatement role = conn.prepareStatement("DROP ROLE IF EXISTS \"" + loja_cnpj.toLowerCase() + "\";");
            int result = role.executeUpdate();
            PreparedStatement inser = conn.prepareStatement("DELETE FROM shopping.loja WHERE cnpj = ?");
            inser.setString(1, loja_cnpj);
            result = inser.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void setSetorFisico(SetorFisico f) {
        this.setorF = f;
    }

    public static class ListarLojas {

        public ArrayList<Loja> getLojas(Connection conn) {
            ArrayList<Loja> list = new ArrayList();
            try {
                PreparedStatement query = conn.prepareStatement("SELECT nome, cnpj from shopping.Loja");

                ResultSet set = query.executeQuery();
                while (set.next()) {
                    list.add(new Loja(set.getString("cnpj"), set.getString("nome")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
    }

    public static class Query {

        Connection conn;

        public Query(Connection conn) {
            this.conn = conn;
        }

        public Loja getLojaByCnpj(String cnpj) {
            Loja loja = null;
            try {
                PreparedStatement query = conn.prepareStatement("SELECT * from Loja l,Setor_Fisico f WHERE l.cnpj = ? and f.id = l.setor_fisico");
                query.setString(1, cnpj);
                ResultSet set = query.executeQuery();
                while (set.next()) {
                    loja = new Loja(set.getString("cnpj"), set.getString("nome"), set.getString("setor_comercial"), set.getInt("setor_fisico"));
                    SetorFisico f = new SetorFisico(set.getInt("id"), set.getDouble("custo"), set.getInt("andar"));
                    loja.setSetorFisico(f);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return loja;
        }
    }
}
