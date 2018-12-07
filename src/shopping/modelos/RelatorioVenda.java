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
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author a11711BCC033
 */
@MappedSuperclass
@Table(name = "relatorio_venda")
@XmlRootElement
public class RelatorioVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RelatorioVendaPK relatorioVendaPK;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private short quantidade;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "cliente", referencedColumnName = "cpf", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente1;
    @JoinColumn(name = "vendedor", referencedColumnName = "cpf", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Funcionario funcionario;
    @JoinColumn(name = "loja", referencedColumnName = "cnpj", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Loja loja1;
    @JoinColumn(name = "produto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto1;

    public RelatorioVenda() {
    }

    public RelatorioVenda(RelatorioVendaPK relatorioVendaPK) {
        this.relatorioVendaPK = relatorioVendaPK;
    }

    public RelatorioVenda(RelatorioVendaPK relatorioVendaPK, short quantidade, Date data) {
        this.relatorioVendaPK = relatorioVendaPK;
        this.quantidade = quantidade;
        this.data = data;
    }

    public RelatorioVenda(String cliente, String vendedor, String loja, int produto) {
        this.relatorioVendaPK = new RelatorioVendaPK(cliente, vendedor, loja, produto);
    }

    public RelatorioVendaPK getRelatorioVendaPK() {
        return relatorioVendaPK;
    }

    public void setRelatorioVendaPK(RelatorioVendaPK relatorioVendaPK) {
        this.relatorioVendaPK = relatorioVendaPK;
    }

    public short getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(short quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Loja getLoja1() {
        return loja1;
    }

    public void setLoja1(Loja loja1) {
        this.loja1 = loja1;
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
        hash += (relatorioVendaPK != null ? relatorioVendaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelatorioVenda)) {
            return false;
        }
        RelatorioVenda other = (RelatorioVenda) object;
        if ((this.relatorioVendaPK == null && other.relatorioVendaPK != null) || (this.relatorioVendaPK != null && !this.relatorioVendaPK.equals(other.relatorioVendaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shopping.RelatorioVenda[ relatorioVendaPK=" + relatorioVendaPK + " ]";
    }
    
}
