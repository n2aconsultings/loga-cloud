package com.loga.enterpriseservice.entity;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "office")
public class Office implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "ifu", unique = true)
    private String ifu;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "address")
    private String address;

    @OneToOne(targetEntity = Employee.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager", referencedColumnName = "id", unique = true)
    private Employee manager;

    @OneToMany(targetEntity = Department.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "office", referencedColumnName = "id")
    private List<Department> departments = new ArrayList<>();

    @OneToMany(targetEntity = Assets.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "office", referencedColumnName = "id")
    private List<Assets> assets = new ArrayList<>();
}
