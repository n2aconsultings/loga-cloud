package com.loga.financeservice.service;

import com.loga.financeservice.entity.Payment;

import java.util.List;

public interface IPaymentService {
    Payment create(Payment payment);
    List<Payment> list();
}
