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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Funcionario {

    private String cpf;
    private String nome;
    private float salario;
    private String funcao;
    private String endereço;
    private String telefone;
    private Loja loja;

    public Funcionario() {
    }

    public Funcionario(String cpf) {
        this.cpf = cpf;
    }

    public Funcionario(String cpf, String nome, float salario, String funcao, String endereço, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
        this.funcao = funcao;
        this.endereço = endereço;
        this.telefone = telefone;
    }

    public Funcionario(String cpf, String nome, float salario, String funcao) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
        this.funcao = funcao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    @Override
    public String toString() {
        return "CPF: "+cpf+" - "+nome;
    }

    public boolean inserir(Connection conn) {
        try {
            PreparedStatement query = conn.prepareStatement("insert into funcionario values(?, ?, ?, ?, ?, ?, ?)");
            query.setString(1, cpf);
            query.setString(2, loja.getCnpj());
            query.setString(3, nome);
            query.setFloat(4, salario);
            query.setString(5, funcao);
            query.setString(6, endereço);
            query.setString(7, telefone);
            query.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static class Query {
        Connection conn;
        public Query(Connection conn){
            this.conn = conn;
        }
        public ArrayList<Funcionario> getFuncionariosPorLoja(String loja_cnpj) {
            ArrayList<Funcionario> list = new ArrayList();
            try {
                PreparedStatement query = conn.prepareStatement("SELECT cpf, l.nome as l_nome, l.cnpj, f.nome, salario,f.telefone as f_tel, funcao, telefone FROM funcionario f, loja l WHERE l.cnpj = f.loja AND f.loja = ?");
                query.setString(1, loja_cnpj);
                ResultSet set = query.executeQuery();
                while (set.next()) {
                    Loja l = new Loja(set.getString("cnpj"), set.getString("l_nome"));
                    Funcionario f = new Funcionario(set.getString("cpf"),set.getString("nome"), set.getFloat("salario"), set.getString("funcao"));
                    f.setTelefone(set.getString("f_tel"));
                    f.setLoja(l);
                    list.add(f);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
        
        public ArrayList<Funcionario> getFuncionariosPorFuncao(String loja_cnpj,String funcao) {
            ArrayList<Funcionario> list = new ArrayList();
            try {
                PreparedStatement query = conn.prepareStatement("SELECT cpf, l.nome as l_nome, l.cnpj, f.nome, salario, funcao, telefone FROM funcionario f, loja l WHERE l.cnpj = ? and l.cnpj = f.loja AND f.funcao = ?");
                query.setString(1, loja_cnpj);
                query.setString(2, funcao);
                ResultSet set = query.executeQuery();
                while (set.next()) {
                    Loja l = new Loja(set.getString("cnpj"), set.getString("l_nome"));
                    Funcionario f = new Funcionario(set.getString("cpf"),set.getString("nome"), set.getFloat("salario"), set.getString("funcao"));
                    f.setLoja(l);
                    list.add(f);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }

        public  ArrayList<Funcionario> getAllFuncionariosPorFuncao(String funcao) {
             ArrayList<Funcionario> list = new ArrayList();
            try {
                PreparedStatement query = conn.prepareStatement("SELECT cpf, l.nome as l_nome, l.cnpj, f.nome, salario, funcao, telefone FROM funcionario f, loja l WHERE l.cnpj = f.loja AND f.funcao = ?");
                query.setString(1, funcao);
                ResultSet set = query.executeQuery();
                while (set.next()) {
                    Loja l = new Loja(set.getString("cnpj"), set.getString("l_nome"));
                    Funcionario f = new Funcionario(set.getString("cpf"),set.getString("nome"), set.getFloat("salario"), set.getString("funcao"));
                    f.setLoja(l);
                    list.add(f);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
    }
}
