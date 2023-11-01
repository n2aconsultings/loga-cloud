package com.loga.financeservice.service;

import com.loga.financeservice.entity.Payment;
import com.loga.financeservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentService implements IPaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @Transactional
    public Payment create(Payment bill) {
        return paymentRepository.save(bill);
    }

    @Override
    public List<Payment> list() {
        return paymentRepository.findAll();
    }
}
