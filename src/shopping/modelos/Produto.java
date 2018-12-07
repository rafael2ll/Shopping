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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "produto")
@XmlRootElement
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "preco")
    private float preco;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "fornecedor", referencedColumnName = "cnpj")
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto1")
    private Collection<RelatorioVenda> relatorioVendaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto1")
    private Collection<Inventario> inventarioCollection;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(Integer id, float preco, String nome, String descricao) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @XmlTransient
    public Collection<RelatorioVenda> getRelatorioVendaCollection() {
        return relatorioVendaCollection;
    }

    public void setRelatorioVendaCollection(Collection<RelatorioVenda> relatorioVendaCollection) {
        this.relatorioVendaCollection = relatorioVendaCollection;
    }

    @XmlTransient
    public Collection<Inventario> getInventarioCollection() {
        return inventarioCollection;
    }

    public void setInventarioCollection(Collection<Inventario> inventarioCollection) {
        this.inventarioCollection = inventarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shopping.Produto[ id=" + id + " ]";
    }
    
}
