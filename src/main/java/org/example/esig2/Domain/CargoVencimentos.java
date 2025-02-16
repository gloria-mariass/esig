package org.example.esig2.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "cargo_vencimentos", schema = "public")
public class CargoVencimentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "vencimento_id", nullable = false)
    private Vencimento vencimento;

    // Getters e Setters
}

