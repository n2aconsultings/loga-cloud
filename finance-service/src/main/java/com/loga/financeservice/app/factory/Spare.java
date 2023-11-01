package com.loga.financeservice.app.factory;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Spare implements Serializable, IItem
{
    private Long id;

    private String designation;

    private Integer price;

    private Float quantity;

    private Integer amount;

    @Override
    public String getName() {
        return getDesignation();
    }

    @Override
    public String getTaxGroup() {
        return "E";
    }
}
