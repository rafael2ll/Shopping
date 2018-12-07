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
public class RelatorioVendaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cliente")
    private String cliente;
    @Basic(optional = false)
    @Column(name = "vendedor")
    private String vendedor;
    @Basic(optional = false)
    @Column(name = "loja")
    private String loja;
    @Basic(optional = false)
    @Column(name = "produto")
    private int produto;

    public RelatorioVendaPK() {
    }

    public RelatorioVendaPK(String cliente, String vendedor, String loja, int produto) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.produto = produto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliente != null ? cliente.hashCode() : 0);
        hash += (vendedor != null ? vendedor.hashCode() : 0);
        hash += (loja != null ? loja.hashCode() : 0);
        hash += (int) produto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelatorioVendaPK)) {
            return false;
        }
        RelatorioVendaPK other = (RelatorioVendaPK) object;
        if ((this.cliente == null && other.cliente != null) || (this.cliente != null && !this.cliente.equals(other.cliente))) {
            return false;
        }
        if ((this.vendedor == null && other.vendedor != null) || (this.vendedor != null && !this.vendedor.equals(other.vendedor))) {
            return false;
        }
        if ((this.loja == null && other.loja != null) || (this.loja != null && !this.loja.equals(other.loja))) {
            return false;
        }
        if (this.produto != other.produto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shopping.RelatorioVendaPK[ cliente=" + cliente + ", vendedor=" + vendedor + ", loja=" + loja + ", produto=" + produto + " ]";
    }
    
}
