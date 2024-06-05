package com.example.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "labels")
@Data
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;

}
