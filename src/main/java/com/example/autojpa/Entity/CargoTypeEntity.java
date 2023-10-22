package com.example.autojpa.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "cargotype")
public class CargoTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public CargoTypeEntity() {

    }

    @Override
    public String toString() {
        return "CargoTypeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
