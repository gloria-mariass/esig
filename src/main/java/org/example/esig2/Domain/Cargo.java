package org.example.esig2.Domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cargo", schema = "public")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100, unique = true)
    private String nome;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL)
    private List<CargoVencimentos> cargoVencimentos; // Adicione este atributo

    // Getters e Setters
    public List<CargoVencimentos> getCargoVencimentos() {
        return cargoVencimentos;
    }

    public void setCargoVencimentos(List<CargoVencimentos> cargoVencimentos) {
        this.cargoVencimentos = cargoVencimentos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Getters e Setters
}

