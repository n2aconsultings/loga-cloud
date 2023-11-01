package com.loga.logisticservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product product;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private Integer amount;

    public void setAmount(){
        if(this.price!=0 && this.quantity!=0)
            this.amount = this.price * this.quantity;
        this.amount = 0;
    }

    public Integer getAmount(){
        if(this.price!=0 && this.quantity!=0)
            return this.price * this.quantity;
        return 0;
    }
}