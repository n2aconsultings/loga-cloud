package com.loga.financeservice.entity;

import com.loga.financeservice.app.factory.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice")
public class Invoice implements Serializable,IInvoice
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", length = 24, unique = true)
    protected String reference;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "dossier")
    private String dossier;

    @OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice", referencedColumnName = "id")
    private List<IItem> items = new ArrayList<>();

    @OneToMany(targetEntity = Payment.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "invoice", referencedColumnName = "id")
    private List<IPayment> payments = new ArrayList<>();

    public void addItem(IItem item){
        items.add(item);
    }

    public void addPayment(IPayment payment){
        payments.add(payment);
    }

    @Override
    public List<IPayment> getPayment() {
        return getPayments();
    }
}
