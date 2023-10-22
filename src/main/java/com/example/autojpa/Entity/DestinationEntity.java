package com.example.autojpa.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "destination")
public class DestinationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    public DestinationEntity() {

    }

    @Override
    public String toString() {
        return "DestinationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
