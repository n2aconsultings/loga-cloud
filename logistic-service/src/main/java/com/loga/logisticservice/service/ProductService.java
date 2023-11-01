package com.loga.logisticservice.service;

import com.loga.logisticservice.entity.Product;
import com.loga.logisticservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> list(String designation) {
        return productRepository.findAllByDesignationContaining(designation);
    }

    @Override
    public Product read(Long id) {
        if(productRepository.findById(id).isPresent())
            return productRepository.findById(id).get();
        return null;
    }

    @Override
    public Product read(String reference) {
        return productRepository.findByReference(reference);
    }

    @Override
    public void edit(Product product, Long id) {
        productRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setReference(product.getReference());
                    up.setCategory(product.getCategory());
                    up.setBrand(product.getBrand());
                    up.setDesignation(product.getDesignation());
                    up.setDescription(product.getDescription());
                    up.setPrice(product.getPrice());
                    up.setStock(product.getStock());
                    up.setReserve(product.getReserve());
                    productRepository.saveAndFlush(up);
                });
    }
}
