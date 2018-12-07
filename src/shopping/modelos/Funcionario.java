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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "funcionario")
@XmlRootElement
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "salario")
    private float salario;
    @Basic(optional = false)
    @Column(name = "funcao")
    private String funcao;
    @Basic(optional = false)
    @Column(name = "endereço")
    private String endereço;
    @Basic(optional = false)
    @Column(name = "telefone")
    private String telefone;
    @ManyToMany(mappedBy = "funcionarioCollection")
    private Collection<Loja> lojaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario")
    private Collection<RelatorioVenda> relatorioVendaCollection;
    @JoinColumn(name = "loja", referencedColumnName = "cnpj")
    @ManyToOne(optional = false)
    private Loja loja;

    public Funcionario() {
    }

    public Funcionario(String cpf) {
        this.cpf = cpf;
    }

    public Funcionario(String cpf, String nome, float salario, String funcao, String endereço, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
        this.funcao = funcao;
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

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
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
    public Collection<Loja> getLojaCollection() {
        return lojaCollection;
    }

    public void setLojaCollection(Collection<Loja> lojaCollection) {
        this.lojaCollection = lojaCollection;
    }

    @XmlTransient
    public Collection<RelatorioVenda> getRelatorioVendaCollection() {
        return relatorioVendaCollection;
    }

    public void setRelatorioVendaCollection(Collection<RelatorioVenda> relatorioVendaCollection) {
        this.relatorioVendaCollection = relatorioVendaCollection;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
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
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.cpf == null && other.cpf != null) || (this.cpf != null && !this.cpf.equals(other.cpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shopping.Funcionario[ cpf=" + cpf + " ]";
    }
    
}
