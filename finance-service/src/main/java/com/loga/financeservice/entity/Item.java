package com.loga.financeservice.entity;

import com.loga.financeservice.app.factory.IItem;
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
@Table(name = "item")
public class Item implements Serializable, IItem
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "tax_group")
    private String taxGroup;

    @Override
    public Integer getPrice() {
        return getAmount();
    }
}
