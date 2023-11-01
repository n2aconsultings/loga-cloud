package com.loga.logisticservice.app.factory;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Spare implements Serializable
{
    private Long id;
    private String designation;
    private Integer price;
    private Integer quantity;
    private Integer amount;
}
