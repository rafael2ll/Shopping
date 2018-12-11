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
import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

public class Inventario {

    private int quantidade;
    private Loja loja;
    private Produto produto;

    public Inventario() {
    }

    public Inventario(int quantidade, Loja loja, Produto produto) {
        this.quantidade = quantidade;
        this.loja = loja;
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto1(Produto produto1) {
        this.produto = produto1;
    }

    @Override
    public String toString() {
        return produto.getNome() + " : " + getQuantidade();
    }

    public boolean inserir(Connection conn) {
        try {
            PreparedStatement inser = conn.prepareStatement("INSERT INTO inventario VALUES(?, ?, ?)");
            inser.setInt(1, produto.getId());
            inser.setString(2, loja.getCnpj());
            inser.setInt(3, quantidade);
            inser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static class Query {

        private final Connection conn;

        public Query(Connection c) {
            this.conn = c;
        }

        public ArrayList<Inventario> getInventarios_loja(String loja_cnpj) {
            ArrayList<Inventario> list = new ArrayList();
            try {
                PreparedStatement query = conn.prepareStatement("SELECT p.nome, p.id, p.preco,quantidade from inventario i, produto p where p.id = i.produto and i.loja_cnpj = ?");
                query.setString(1, loja_cnpj);
                ResultSet set = query.executeQuery();
                while (set.next()) {
                    Loja j = new Loja();
                    j.setCnpj(loja_cnpj);
                    Produto p = new Produto();
                    p.setId(set.getInt("id"));
                    p.setNome(set.getString("nome"));
                    p.setPreco(set.getFloat("preco"));
                    Inventario i = new Inventario(set.getInt("quantidade"), j, p);
                    list.add(i);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
    }
}
