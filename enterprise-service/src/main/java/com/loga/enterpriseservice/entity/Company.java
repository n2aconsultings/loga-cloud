package com.loga.enterpriseservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "legal", unique = true)
    private String legal;

    @Column(name = "location")
    private String location;

    @OneToMany(targetEntity = Office.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "company", referencedColumnName = "id")
    private List<Office> offices = new ArrayList<>();

    @OneToMany(targetEntity = Contract.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "company", referencedColumnName = "id")
    private List<Contract> contracts = new ArrayList<>();
}

