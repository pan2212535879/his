package com.abc.His02.service.OutpatientFinancial;

import com.abc.His02.entity.ExpenseClass;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IExpenseclassService{
    List<ExpenseClass> selectAll(ExpenseClass expenseClass);
    ExpenseClass selectById(ExpenseClass expenseClass);
    int add(ExpenseClass expenseClass);
    int update(ExpenseClass expenseClass);
    int delete(ExpenseClass expenseClass);
}
