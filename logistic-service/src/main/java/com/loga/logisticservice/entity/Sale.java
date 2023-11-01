package com.loga.logisticservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale")
public class Sale implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sale_at")
    private Date saleAt;

    @Column(name = "client")
    private String client;

    @OneToMany(targetEntity = Item.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="sale", referencedColumnName="id")
    private List<Item> items = new ArrayList<>();
}
