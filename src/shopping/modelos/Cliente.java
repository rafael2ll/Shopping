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
import javax.persistence.Id;
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
@Table(name = "cliente")
@XmlRootElement
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "endereço")
    private String endereço;
    @Basic(optional = false)
    @Column(name = "telefone")
    private String telefone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente1")
    private Collection<RelatorioVenda> relatorioVendaCollection;

    public Cliente() {
    }

    public Cliente(String cpf) {
        this.cpf = cpf;
    }

    public Cliente(String cpf, String nome, String endereço, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereço = endereço;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @XmlTransient
    public Collection<RelatorioVenda> getRelatorioVendaCollection() {
        return relatorioVendaCollection;
    }

    public void setRelatorioVendaCollection(Collection<RelatorioVenda> relatorioVendaCollection) {
        this.relatorioVendaCollection = relatorioVendaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpf != null ? cpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cpf == null && other.cpf != null) || (this.cpf != null && !this.cpf.equals(other.cpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shopping.Cliente[ cpf=" + cpf + " ]";
    }
    
}
