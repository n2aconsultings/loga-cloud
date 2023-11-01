package com.loga.financeservice.service;

import com.loga.financeservice.entity.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private IPaymentService paymentService;

    @Test
    void create() {
        Payment payment = paymentService.create(new Payment(null,new Date(),"especes","premier paiement",10000));
        System.out.println(payment);
        assertNotNull(payment,"Failed");
    }
}