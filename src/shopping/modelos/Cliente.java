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
import javax.xml.bind.annotation.XmlTransient;

public class Cliente implements Serializable {

    private String cpf;
    private String nome;
    private String endereço;
    private String telefone;

    public Cliente() {
    }

    public Cliente(String cpf) {
        this.cpf = cpf;
    }

    public Cliente(String cpf, String nome, String endereço, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereço = endereço;
        this.telefone = telefone;
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

    @Override
    public String toString() {
        return cpf+" - "+nome;
    }

    public static  class Query {    
        Connection conn;
        
        public Query(Connection c){
            this.conn  = c;
        }
        public ArrayList<Cliente> getClientes(){
            ArrayList<Cliente> list = new ArrayList<>();
            try{
                PreparedStatement query = conn.prepareStatement("SELECT cpf,nome, endereco,telefone from Cliente");
              
                ResultSet set = query.executeQuery();
                while(set.next()){
                    list.add(new Cliente(set.getString("cpf"),set.getString("nome"),set.getString("endereco"),set.getString("telefone")));
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return list;
        }
        
        public ArrayList<Cliente> ClientesQuemaisCompraramPorLoja(String loja_cnpj)
        {
            ArrayList<Cliente> List = new ArrayList<>();
            try{
                PreparedStatement query = conn.prepareStatement("select cliente.nome, cliente.cpf, cliente.telefone,cliente.endereco from cliente, relatoriovenda where cliente.cpf = relatoriovenda.cliente order by (relatoriovenda.quantidade) desc limit 3");
                
                ResultSet set = query.executeQuery();
                
                while(set.next())
                {
                    List.add(new Cliente(set.getString("cliente.cpf"),set.getString("cliente.nome"),set.getString("cliente.endereco"),set.getString("cliete.telefone")));
                }
            }catch(SQLException se){
                se.getStackTrace();
            }
            return List;
        }
        
    }
}
