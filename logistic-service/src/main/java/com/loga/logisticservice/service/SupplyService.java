package com.loga.logisticservice.service;

import com.loga.logisticservice.entity.Article;
import com.loga.logisticservice.entity.Delivery;
import com.loga.logisticservice.entity.Furnisher;
import com.loga.logisticservice.entity.Order;
import com.loga.logisticservice.repository.DeliveryRepository;
import com.loga.logisticservice.repository.FurnisherRepository;
import com.loga.logisticservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyService implements ISupplyService {

    private final FurnisherRepository furnisherRepository;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public SupplyService(FurnisherRepository furnisherRepository,
                         OrderRepository orderRepository,
                         DeliveryRepository deliveryRepository) {
        this.furnisherRepository = furnisherRepository;
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Furnisher registrateFurnisher(Furnisher furnisher) {
        return furnisherRepository.save(furnisher);
    }

    @Override
    public Furnisher readFurnisher(Long id) {
        if(furnisherRepository.findById(id).isPresent())
            return furnisherRepository.findById(id).get();
        return null;
    }

    @Override
    public List<Furnisher> listFurnisher() {
        return furnisherRepository.findAll();
    }

    @Override
    public void editFurnisher(Furnisher furnisher, Long id) {
        furnisherRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setName(furnisher.getName());
                    up.setAddress(furnisher.getAddress());
                    up.setContact(furnisher.getContact());
                    furnisherRepository.saveAndFlush(up);
                });
    }

    @Override
    public void deleteFurnisher(Long id) {
        furnisherRepository.deleteById(id);
    }

    @Override
    public Order registrateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> listOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> listOrder(String reference) {
        return orderRepository.findAllByReferenceContaining(reference);
    }

    @Override
    public Order readOrder(Long id) {
        if(orderRepository.findById(id).isPresent())
            return orderRepository.findById(id).get();
        return null;
    }

    @Override
    public void editOrder(Order order, Long id) {
        orderRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setReference(order.getReference());
                    up.setArticles(order.getArticles());
                    up.setDeliveries(order.getDeliveries());
                    up.setFurnisher(order.getFurnisher());
                    orderRepository.saveAndFlush(up);
                });
    }

    @Override
    public void editOrder(Article article, Long id) {
        orderRepository
                .findById(id)
                .ifPresent(order -> {
                    order.setReference(order.getReference());
                    order.getArticles().add(article);
                    order.setDeliveries(order.getDeliveries());
                    order.setFurnisher(order.getFurnisher());
                    orderRepository.saveAndFlush(order);
                });
    }

    @Override
    public void editOrder(Delivery delivery, Long id){
        Order order = readOrder(id);
        for (Article article : delivery.getArticles()) {
            if(order.getArticles().contains(article)){
                article.getProduct().setStock(
                        article.getProduct().getStock() + article.getQuantity()
                );
                article.getProduct().setPrice(article.getPrice());
                article.setDelivered(true);
            }
        }
        order.getDeliveries().add(delivery);
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Delivery registrateDelivery(Delivery delivery) {
        for (Article article : delivery.getArticles()) {
            article.getProduct().setStock(article.getProduct().getStock()+article.getQuantity());
            article.getProduct().setPrice(article.getPrice());
        }
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery readDelivery(Long id) {
        if(deliveryRepository.findById(id).isPresent())
            return deliveryRepository.findById(id).get();
        return null;
    }

    @Override
    public List<Delivery> listDelivery() {
        return deliveryRepository.findAll();
    }

    @Override
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
