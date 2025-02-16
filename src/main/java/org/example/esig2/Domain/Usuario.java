package org.example.esig2.Domain;

import jakarta.persistence.*;
import org.example.esig2.DTO.UsuarioDTO;

import java.util.Date;

@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(name = "usuario_email_key", columnNames = "email"),
        @UniqueConstraint(name = "usuario_usuario_key", columnNames = "usuario")
}, schema = "public")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 100)
    private String cidade;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(length = 20)
    private String cep;

    @Column(length = 200)
    private String endereco;

    @Column(length = 50)
    private String pais;

    @Column(nullable = false, length = 50)
    private String usuario;

    @Column(length = 20)
    private String telefone;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public String getUsuario() {
        return usuario;
    }


    // Getters e Setters
}

