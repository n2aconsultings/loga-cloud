package com.loga.financeservice.app.factory;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable,IItem
{
    private Long id;

    private String description;

    private Integer cost;

    private Integer duration;

    @Override
    public String getName() {
        return getDescription();
    }

    @Override
    public Integer getPrice() {
        return getCost();
    }

    @Override
    public Float getQuantity() {
        return 1F;
    }

    @Override
    public String getTaxGroup() {
        return "E";
    }
}
