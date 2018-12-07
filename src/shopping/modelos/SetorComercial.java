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


public class SetorComercial {

    private String nome;
    private String descricao;
   
    public SetorComercial() {
    }

    public SetorComercial(String nome) {
        this.nome = nome;
    }

    public SetorComercial(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nome != null ? nome.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SetorComercial)) {
            return false;
        }
        SetorComercial other = (SetorComercial) object;
        if ((this.nome == null && other.nome != null) || (this.nome != null && !this.nome.equals(other.nome))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    public ArrayList<Loja> getLojasDoSetor(Connection conn){
        ArrayList<Loja> list = new ArrayList();
            try{
                PreparedStatement query = conn.prepareStatement("SELECT * from Loja WHERE setor_comercial = ?");
                query.setString(1, nome);
                ResultSet set = query.executeQuery();
                while(set.next()){
                    list.add(new Loja(set.getString("cnpj"), set.getString("nome")));
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return list;
    }
    
    public static class Query{
        public ArrayList<SetorComercial> getSetoresComerciais(Connection conn){
            ArrayList<SetorComercial> list = new ArrayList();
            try{
                PreparedStatement query = conn.prepareStatement("SELECT * from Setor_Comercial");
              
                ResultSet set = query.executeQuery();
                while(set.next()){
                    list.add(new SetorComercial(set.getString("nome"), set.getString("descricao")));
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return list;
        }
     
    }
}
