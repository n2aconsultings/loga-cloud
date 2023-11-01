package com.loga.invoiceservice.app.factory;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeDto {
    String type;
    String description;
}
