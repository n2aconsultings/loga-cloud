package com.loga.financeservice.app.factory;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Repair implements Serializable
{
    private Long id;

    private String dossier;

    private String profile;

    private Date createdAt;

    private String reference;

    private String description;

    private Integer mileage;

    private Boolean approved;

    private List<Task> tasks = new ArrayList<>();

    private List<Spare> spares = new ArrayList<>();
}
