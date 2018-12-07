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

public class Loja{

    private String cnpj;
    private String nome;
    private String setorComercial;
    private int setorFisico;

    public Loja() {
    }

    public Loja(String cnpj,String nome) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Loja(String cnpj, String nome,String setor_comercial, int setor_fisico_id) {
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

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString(){
        return nome+"/"+cnpj;
    }
    public boolean inserir(Connection conn, String loja_id, String loja_pwd){
        try{
            
            PreparedStatement role = conn.prepareStatement("CREATE ROLE "+loja_id+ " WITH PASSWORD '"+loja_pwd+"'");
            role.execute();
         
            PreparedStatement inser = conn.prepareStatement("INSERT INTO loja VALUES(?, ?, ?, ?)");
            inser.setString(1, cnpj);
            inser.setString(2, setorComercial);
            inser.setInt(3, setorFisico);
            inser.setString(4, nome);
            inser.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
     
    public static boolean remover(Connection conn, String loja_id, String loja_cnpj){
        try{
            conn.setAutoCommit(false);
            
            PreparedStatement role = conn.prepareStatement("DROP ROLE ?");
            role.setString(1, loja_id);
            int result = role.executeUpdate();
            if(result > 0 ){
            PreparedStatement inser = conn.prepareStatement("DELETE FROM loja WHERE cnpj = ?");
            inser.setString(1, loja_cnpj);
            result = inser.executeUpdate();
            if(result <= 0) conn.rollback();
            }else{
                conn.rollback();
            }
            conn.setAutoCommit(true);
        }catch(SQLException e){
            return false;
        }
        return true;
    }
    
    public static  class ListarLojas{    
        public ArrayList<Loja> getLojas(Connection conn){
            ArrayList<Loja> list = new ArrayList();
            try{
                PreparedStatement query = conn.prepareStatement("SELECT nome, cnpj from Loja");
              
                ResultSet set = query.executeQuery();
                while(set.next()){
                    list.add(new Loja(set.getString("cnpj"), set.getString("nome")));
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return list;
        }
    }
}
