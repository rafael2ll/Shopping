/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.modelos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author a11711BCC033
 */
@MappedSuperclass
@Table(name = "inventario")
@XmlRootElement
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InventarioPK inventarioPK;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private int quantidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventario")
    private Collection<Promocao> promocaoCollection;
    @JoinColumn(name = "loja_cnpj", referencedColumnName = "cnpj", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Loja loja;
    @JoinColumn(name = "produto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto1;

    public Inventario() {
    }

    public Inventario(InventarioPK inventarioPK) {
        this.inventarioPK = inventarioPK;
    }

    public Inventario(InventarioPK inventarioPK, int quantidade) {
        this.inventarioPK = inventarioPK;
        this.quantidade = quantidade;
    }

    public Inventario(int produto, String lojaCnpj) {
        this.inventarioPK = new InventarioPK(produto, lojaCnpj);
    }

    public InventarioPK getInventarioPK() {
        return inventarioPK;
    }

    public void setInventarioPK(InventarioPK inventarioPK) {
        this.inventarioPK = inventarioPK;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @XmlTransient
    public Collection<Promocao> getPromocaoCollection() {
        return promocaoCollection;
    }

    public void setPromocaoCollection(Collection<Promocao> promocaoCollection) {
        this.promocaoCollection = promocaoCollection;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Produto getProduto1() {
        return produto1;
    }

    public void setProduto1(Produto produto1) {
        this.produto1 = produto1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventarioPK != null ? inventarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.inventarioPK == null && other.inventarioPK != null) || (this.inventarioPK != null && !this.inventarioPK.equals(other.inventarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shopping.Inventario[ inventarioPK=" + inventarioPK + " ]";
    }
    
}
