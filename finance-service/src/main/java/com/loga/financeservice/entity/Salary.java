package com.loga.financeservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "salary")
public class Salary implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "prime")
    private Integer prime;

    @Column(name = "indemnity")
    private Integer indemnity;

    @Column(name = "additional")
    private Integer additional;

    @Column(name = "tax")
    private Integer tax;

    @Column(name = "mode")
    private String mode;

    @Column(name = "reference", length = 50, unique = true)
    private String reference;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "paid_at")
    private Date paidAt;

    @Column(name = "employee")
    private String employee;
}
