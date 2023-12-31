package com.loga.customerservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class Client implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "legal_notice")
    private String legalNotice;

    @Column(name = "address")
    private String address;

    @Column(name = "contact",unique = true)
    private String contact;

    public Client(String name, String type, String contact){
        this.name=name;
        this.type=type;
        this.contact=contact;
    }
}
