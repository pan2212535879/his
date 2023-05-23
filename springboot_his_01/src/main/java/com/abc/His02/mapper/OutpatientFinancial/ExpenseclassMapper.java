package com.abc.His02.mapper.OutpatientFinancial;

import com.abc.His02.entity.ExpenseClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface ExpenseclassMapper{
    List<ExpenseClass> selectAll(ExpenseClass expenseClass);
    ExpenseClass selectById(ExpenseClass expenseClass);
    int add(ExpenseClass expenseClass);
    int update(ExpenseClass expenseClass);
    int delete(ExpenseClass expenseClass);
}
