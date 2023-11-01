package com.loga.invoiceservice.app.factory;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequestDataDto {
    protected String ifu;
    protected String aib;
    protected String type;
    protected List<ItemDto> items;
    protected ClientDto client;
    protected OperatorDto operator;
    protected List<PaymentDto> payment;
    protected String reference;
}
