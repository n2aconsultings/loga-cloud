package com.loga.financeservice.app.factory;

import java.util.List;

public interface IInvoice {
    String getReference();
    List<IItem> getItems();
    List<IPayment> getPayment();
}
