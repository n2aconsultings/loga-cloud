package com.loga.financeservice.app.factory;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto implements IItem{
    protected String code;
    protected String name;
    protected Integer price;
    protected Float quantity;
    protected String taxGroup;
    protected Integer taxSpecific;
    protected Integer originalPrice;
    protected String priceModification;
}
