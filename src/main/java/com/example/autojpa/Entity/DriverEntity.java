package com.example.autojpa.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "driver")
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "experience", nullable = false)
    private Integer experience;

    @ManyToOne
    @JoinColumn(name = "auto_id")
    private AutoEntity auto;

    @Column(name = "money", nullable = false)
    private Double money;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private RequestEntity request;

    public DriverEntity() {

    }

    public void createRepairRequest(){

    }
}
