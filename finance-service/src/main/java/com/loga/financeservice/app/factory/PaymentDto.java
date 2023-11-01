package com.loga.financeservice.app.factory;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto implements IPayment{
    String paymentType;
    Integer amount;
}
