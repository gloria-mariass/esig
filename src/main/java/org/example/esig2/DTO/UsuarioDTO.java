package org.example.esig2.DTO;

import java.math.BigDecimal;

public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String email;
    private String usuario;
    private String cargo; // Nome do cargo
    private BigDecimal salario;

    // Construtor
    public UsuarioDTO(Integer id, String nome, String email, String usuario, String cargo, BigDecimal salario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.usuario = usuario;
        this.cargo = cargo;
        this.salario = salario;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getCargo() {
        return cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}