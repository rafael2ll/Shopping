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
import java.time.LocalDate;
import java.util.Date;


public class Promocao implements Serializable {

    private float desconto;
    private Inventario inventario;
    private Loja loja;
    private Date dataInicio;
    private Date dataFim;
    public Promocao() {
    }

    public Promocao(float desconto, Inventario inventario, Loja loja, Date dataInicio, Date dataFim) {
        this.desconto = desconto;
        this.inventario = inventario;
        this.loja = loja;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

  
    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public String toString() {
        return "R$"+desconto;
    }

    public static Promocao isInPromocao(Connection conn, String cnpj, Integer id) {
        Promocao p = null;
         try{
                PreparedStatement query = conn.prepareStatement("SELECT desconto FROM promocao WHERE loja_cnpj = ? and produto = ? and data_inicio <= ? and data_fim >= ?");
                query.setString(1, cnpj);
                query.setInt(2, id);
                query.setDate(3,java.sql.Date.valueOf(LocalDate.now()));
                query.setDate(4,java.sql.Date.valueOf(LocalDate.now()));
               
                ResultSet set = query.executeQuery();
                while(set.next()){
                    System.out.print(set.getFloat("desconto"));
                    p = new Promocao();
                    p.setDesconto(set.getFloat("desconto"));
                }
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        return p;
    }
    
}
