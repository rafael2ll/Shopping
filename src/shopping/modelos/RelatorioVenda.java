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
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;


public class RelatorioVenda {

    public RelatorioVenda(Date data, Cliente cliente, Funcionario funcionario, Loja loja, short quantidade, Produto produto) {
        this.data = data;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.loja = loja;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    private Date data;
    private Cliente cliente;
    private Funcionario funcionario;
    private Loja loja;
    private short quantidade;
    private Produto produto;

    public RelatorioVenda() {
    }

    public short getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(short quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setCliente(Cliente cliente1) {
        this.cliente = cliente1;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja1) {
        this.loja = loja1;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto1(Produto produto1) {
        this.produto = produto1;
    }

    public boolean criar_relatorio(Connection conn){
           try {
                PreparedStatement query = conn.prepareStatement("INSERT INTO relatorio_venda VALUES(?,?,?,?,?,?)");
                
                query.setString(1, cliente.getCpf());
                query.setString(2, funcionario.getCpf());
                query.setString(3, loja.getCnpj());
                query.setInt(4, produto.getId());
                query.setInt(5, quantidade);
                query.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
               
                query.execute();
                
           }catch(SQLException e){
               e.printStackTrace();
               return false;
           }
           return true;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public static class Query{

        Connection conn;

        public Query(Connection conn) {
            this.conn = conn;
        }
        
        
        public ArrayList<RelatorioVenda> getRelarioVendaLoja(String loja_cnpj) {
            ArrayList<RelatorioVenda> List = new ArrayList<>();

            try {
                PreparedStatement query = conn.prepareStatement("select c.nome as c_nome, f.nome as f_nome, p.nome as p_nome, p.preco as p_preco,l.nome as l_nome, data, quantidade from Relatorio_Venda v, Loja l, Produto p, Cliente c, Funcionario f "
                        + " where v.loja = ? and p.id = v.produto and l.cnpj = v.loja and f.cpf = v.vendedor and c.cpf = v.cliente");
                query.setString(1, loja_cnpj);

                ResultSet set = query.executeQuery();
                while (set.next()) {
                    Loja l = new Loja();
                    l.setNome(set.getString("l_nome"));
                    l.setCnpj(loja_cnpj);
                    Cliente c = new Cliente();
                    c.setNome(set.getString("c_nome"));
                    Produto p = new Produto();
                    p.setNome(set.getString("p_nome"));
                    p.setPreco(set.getFloat("p_preco"));
                    Funcionario f = new Funcionario();
                    f.setNome(set.getString("f_nome"));
                    List.add(new RelatorioVenda(set.getDate("data"), c, f, l, set.getShort("quantidade"), p));
                }

            } catch (SQLException se) {
                se.getStackTrace();
            }
            return List;
        }

        public ArrayList<RelatorioVenda> getRelarioVendaPorLoja(String loja_cnpj) {
            ArrayList<RelatorioVenda> List = new ArrayList<>();

            try {
                PreparedStatement query = conn.prepareStatement("select cliente,vendedor,produto,quantidade,loja from RelatorioVenda WHERE loja = ?");
                query.setString(1, loja_cnpj);
                ResultSet set = query.executeQuery();
                while (set.next()) {
                   // List.add(new RelatorioVenda(set.getString("cliente"), set.getString("vendedor"), set.getString("loja"), set.getInt("produto"), set.getShort("quantidade")));
                }

            } catch (SQLException se) {
                se.getStackTrace();
            }
            return List;
        }
         public Float getTotalDeVendasPorDia(String loja_cnpj, Date data) {
            Float valor = 0f;
            try {
                PreparedStatement query = conn.prepareStatement("select totalVendasPorDia(?,?)");
                query.setString(1, loja_cnpj);
                query.setDate(2, data);
                System.out.println(query.toString());
                ResultSet set = query.executeQuery();
                while (set.next()) {
                       valor = set.getFloat(0);
                }

            } catch (SQLException se) {
                se.getStackTrace();
            }
            return valor;
        }
    }
}
