package com.loga.enterpriseservice.entity;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assets")
public class Assets implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", unique = true)
    private String reference;

    @Column(name = "name")
    private String name;

    @Column(name = "entry_at")
    private Date entryAt;
}
