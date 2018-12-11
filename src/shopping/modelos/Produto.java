/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.modelos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

public class Produto implements Serializable {

    private Integer id;
    private float preco;
    private String nome;
    private String descricao;
    private Fornecedor fornecedor;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(Integer id, float preco, String nome, String descricao) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "ID:" + id + "- " + nome;
    }

    public static class Query {

        private final Connection conn;

        public Query(Connection connection) {
            this.conn = connection;
        }

        public ArrayList<Produto> getProdutosFromLoja(String cnpj) {
            ArrayList<Produto> list = new ArrayList<>();
            try {
                PreparedStatement query = conn.prepareStatement("select p.id , p.preco ,p.nome , p.descricao from produto p, inventario i "
                        + "where i.loja_cnpj = ? and i.produto = p.id");
                query.setString(1, cnpj);
                ResultSet resultado = query.executeQuery();

                while (resultado.next()) {
                    list.add(new Produto(resultado.getInt("id"), resultado.getFloat("preco"), resultado.getString("nome"), resultado.getString("descricao")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }

        public List<Produto> getProdutos() {
            ArrayList<Produto> list = new ArrayList<>();
            try {
                PreparedStatement query = conn.prepareStatement("select * from produto");
                ResultSet resultado = query.executeQuery();

                while (resultado.next()) {
                    list.add(new Produto(resultado.getInt("id"), resultado.getFloat("preco"), resultado.getString("nome"), resultado.getString("descricao")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
    }

}
