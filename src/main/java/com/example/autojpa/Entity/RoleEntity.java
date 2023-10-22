package com.example.autojpa.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


}
