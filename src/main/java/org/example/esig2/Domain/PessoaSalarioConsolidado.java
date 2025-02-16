package org.example.esig2.Domain;

import jakarta.persistence.*;
import org.example.esig2.DAO.VencimentoDAO;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pessoa_salario_consolidado", schema = "public")
public class PessoaSalarioConsolidado {

    @Id
    @Column(name = "pessoa_id")
    private Integer pessoaId;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario pessoa;

    @Column(name = "nome_pessoa", nullable = false, length = 255)
    private String nomePessoa;

    @ManyToOne
    @JoinColumn(name = "nome_cargo", referencedColumnName = "nome", nullable = true)
    private Cargo cargo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salario;

    // Getters e Setters
    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Usuario getUsuario() {
        return pessoa;
    }

    public void setUsuario(Usuario pessoa) {
        this.pessoa = pessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
