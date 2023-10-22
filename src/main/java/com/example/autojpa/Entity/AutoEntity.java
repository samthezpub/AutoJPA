package com.example.autojpa.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "auto")
public class AutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cargoQuantity", nullable = false)
    private Double cargoQuantity;

    @ManyToOne()
    @JoinColumn(name = "cargotype_id")
    private CargoTypeEntity cargoType;

    @Column(name = "is_free")
    private boolean isFree;

    public AutoEntity() {

    }
}
