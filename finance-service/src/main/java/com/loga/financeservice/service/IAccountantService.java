package com.loga.financeservice.service;

import com.loga.financeservice.entity.Expense;
import com.loga.financeservice.entity.Recipe;
import com.loga.financeservice.entity.Register;

import java.util.Date;
import java.util.List;

public interface IAccountantService {
    Register create(Register register);
    Register read(Long id);
    Register read(Date date);
    List<Register> list();
    List<Register> list(Date start, Date end);
    void edit(Expense expense, Long id);
    void edit(Recipe recipe, Long id);
    void delete(Long id);
    void deleteRecipe(Long id);
    void deleteSpent(Long id);
}
