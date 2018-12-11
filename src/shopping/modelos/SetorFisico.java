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

public class SetorFisico{

   
    private int id;
    private double custo;
    private int andar;
 
    public SetorFisico() {
    }

    public SetorFisico(int id) {
        this.id = id;
    }

    public SetorFisico(int id, double custo, int andar) {
        this.id = id;
        this.custo = custo;
        this.andar = andar;
    }

    public int getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }


    public String formatSetor(){
         return String.format("Setor %d - %dºAndar", id, andar);
    }
    @Override
    public String toString() {
        return String.format("Setor %d - %dºAndar : R$%f", id, andar, custo);
    }
      public ArrayList<Loja> getLojasDoSetor(Connection conn){
        ArrayList<Loja> list = new ArrayList();
            try{
                PreparedStatement query = conn.prepareStatement("SELECT * from Loja WHERE setor_fisico = ?");
                query.setInt(1, id);
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
        public ArrayList<SetorFisico> getSetoresFisicos(Connection conn){
            ArrayList<SetorFisico> list = new ArrayList();
            try{
                PreparedStatement query = conn.prepareStatement("SELECT * from Setor_Fisico");
              
                ResultSet set = query.executeQuery();
                while(set.next()){
                    list.add(new SetorFisico(set.getInt("id"), set.getDouble("custo"), set.getInt("andar")));
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return list;
        }
     
    }
}
