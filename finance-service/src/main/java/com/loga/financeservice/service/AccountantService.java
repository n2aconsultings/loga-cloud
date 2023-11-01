package com.loga.financeservice.service;

import com.loga.financeservice.entity.*;
import com.loga.financeservice.repository.ExpenseRepository;
import com.loga.financeservice.repository.RecipeRepository;
import com.loga.financeservice.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AccountantService implements IAccountantService {

    private final RegisterRepository registerRepository;
    private final RecipeRepository recipeRepository;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public AccountantService(RegisterRepository registerRepository,
                             RecipeRepository recipeRepository,
                             ExpenseRepository expenseRepository) {
        this.registerRepository = registerRepository;
        this.recipeRepository = recipeRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    @Transactional
    public Register create(Register register) {
        return registerRepository.save(register);
    }

    @Override
    public Register read(Long id) {
        if(registerRepository.findById(id).isPresent())
            return registerRepository.findById(id).get();
        return null;
    }

    @Override
    public Register read(Date date) {
        return registerRepository.findByCreatedAt(date);
    }

    @Override
    public List<Register> list() {
        return registerRepository.findAll();
    }

    @Override
    public List<Register> list(Date start, Date end) {
        return registerRepository.findAllByCreatedAtBetween(start,end);
    }

    @Override
    @Transactional
    public void edit(Expense expense, Long id) {
        registerRepository
                .findById(id)
                .ifPresent(register -> {
                    register.addExpense(expense);
                    registerRepository.saveAndFlush(register);
                });
    }

    @Override
    @Transactional
    public void edit(Recipe recipe, Long id) {
        registerRepository
                .findById(id)
                .ifPresent(register -> {
                    register.addRecipe(recipe);
                    registerRepository.saveAndFlush(register);
                });
    }

    @Override
    public void delete(Long id) {
        registerRepository.deleteById(id);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public void deleteSpent(Long id) {
        expenseRepository.deleteById(id);
    }
}
