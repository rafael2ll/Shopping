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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author a11711BCC033
 */
@Embeddable
public class PromocaoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "loja_cnpj")
    private String lojaCnpj;
    @Basic(optional = false)
    @Column(name = "produto")
    private short produto;
    @Basic(optional = false)
    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Basic(optional = false)
    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;

    public PromocaoPK() {
    }

    public PromocaoPK(String lojaCnpj, short produto, Date dataInicio, Date dataFim) {
        this.lojaCnpj = lojaCnpj;
        this.produto = produto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getLojaCnpj() {
        return lojaCnpj;
    }

    public void setLojaCnpj(String lojaCnpj) {
        this.lojaCnpj = lojaCnpj;
    }

    public short getProduto() {
        return produto;
    }

    public void setProduto(short produto) {
        this.produto = produto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lojaCnpj != null ? lojaCnpj.hashCode() : 0);
        hash += (int) produto;
        hash += (dataInicio != null ? dataInicio.hashCode() : 0);
        hash += (dataFim != null ? dataFim.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PromocaoPK)) {
            return false;
        }
        PromocaoPK other = (PromocaoPK) object;
        if ((this.lojaCnpj == null && other.lojaCnpj != null) || (this.lojaCnpj != null && !this.lojaCnpj.equals(other.lojaCnpj))) {
            return false;
        }
        if (this.produto != other.produto) {
            return false;
        }
        if ((this.dataInicio == null && other.dataInicio != null) || (this.dataInicio != null && !this.dataInicio.equals(other.dataInicio))) {
            return false;
        }
        if ((this.dataFim == null && other.dataFim != null) || (this.dataFim != null && !this.dataFim.equals(other.dataFim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shopping.PromocaoPK[ lojaCnpj=" + lojaCnpj + ", produto=" + produto + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + " ]";
    }
    
}
