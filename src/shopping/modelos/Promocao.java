/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author a11711BCC033
 */
@MappedSuperclass
@Table(name = "promocao")
@XmlRootElement
public class Promocao implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PromocaoPK promocaoPK;
    @Basic(optional = false)
    @Column(name = "desconto")
    private float desconto;
    @JoinColumns({
        @JoinColumn(name = "produto", referencedColumnName = "produto", insertable = false, updatable = false)
        , @JoinColumn(name = "loja_cnpj", referencedColumnName = "loja_cnpj", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Inventario inventario;

    public Promocao() {
    }

    public Promocao(PromocaoPK promocaoPK) {
        this.promocaoPK = promocaoPK;
    }

    public Promocao(PromocaoPK promocaoPK, float desconto) {
        this.promocaoPK = promocaoPK;
        this.desconto = desconto;
    }

    public Promocao(String lojaCnpj, short produto, Date dataInicio, Date dataFim) {
        this.promocaoPK = new PromocaoPK(lojaCnpj, produto, dataInicio, dataFim);
    }

    public PromocaoPK getPromocaoPK() {
        return promocaoPK;
    }

    public void setPromocaoPK(PromocaoPK promocaoPK) {
        this.promocaoPK = promocaoPK;
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
    public int hashCode() {
        int hash = 0;
        hash += (promocaoPK != null ? promocaoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promocao)) {
            return false;
        }
        Promocao other = (Promocao) object;
        if ((this.promocaoPK == null && other.promocaoPK != null) || (this.promocaoPK != null && !this.promocaoPK.equals(other.promocaoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shopping.Promocao[ promocaoPK=" + promocaoPK + " ]";
    }
    
}
