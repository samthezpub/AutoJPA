package com.example.autojpa.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "request")
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private DestinationEntity destination;

    @Column(name = "cargoquantity")
    private Double cargoQuantity;

    @ManyToOne
    @JoinColumn(name = "cargotype_id")
    private CargoTypeEntity cargoType;

    @Column(name = "isDone")
    private boolean isDone = false;

    @Column(name = "experience")
    private Integer experience;


    public RequestEntity() {
    }
}
