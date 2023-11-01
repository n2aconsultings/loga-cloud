package com.loga.enterpriseservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contract")
public class Contract implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference",unique = true)
    private String reference;

    @Column(name = "position")
    private String position;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "start_at")
    private Date startAt;

    @Column(name = "end_at")
    private Date endAt;

    @OneToOne(targetEntity = Employee.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private Employee employee;
}
