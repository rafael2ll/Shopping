/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author a11711BCC033
 */
@Embeddable
public class InventarioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "produto")
    private int produto;
    @Basic(optional = false)
    @Column(name = "loja_cnpj")
    private String lojaCnpj;

    public InventarioPK() {
    }

    public InventarioPK(int produto, String lojaCnpj) {
        this.produto = produto;
        this.lojaCnpj = lojaCnpj;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public String getLojaCnpj() {
        return lojaCnpj;
    }

    public void setLojaCnpj(String lojaCnpj) {
        this.lojaCnpj = lojaCnpj;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) produto;
        hash += (lojaCnpj != null ? lojaCnpj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioPK)) {
            return false;
        }
        InventarioPK other = (InventarioPK) object;
        if (this.produto != other.produto) {
            return false;
        }
        if ((this.lojaCnpj == null && other.lojaCnpj != null) || (this.lojaCnpj != null && !this.lojaCnpj.equals(other.lojaCnpj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shopping.InventarioPK[ produto=" + produto + ", lojaCnpj=" + lojaCnpj + " ]";
    }
    
}
