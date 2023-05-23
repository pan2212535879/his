package com.abc.His02.service.OutpatientFinancial;

import com.abc.His02.entity.ExpenseClass;
import com.abc.His02.mapper.OutpatientFinancial.ExpenseclassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
@Transactional
public class ExpenseclassServiceImpl implements IExpenseclassService {
    @Autowired
    ExpenseclassMapper dao;

    @Override
    public List<ExpenseClass> selectAll(ExpenseClass expenseClass) {
        return dao.selectAll(expenseClass);
    }

    @Override
    public ExpenseClass selectById(ExpenseClass expenseClass) {
        return dao.selectById(expenseClass);
    }

    @Override
    public int add(ExpenseClass expenseClass) {
        return dao.add(expenseClass);
    }

    @Override
    public int update(ExpenseClass expenseClass) {
        return dao.update(expenseClass);
    }

    @Override
    public int delete(ExpenseClass expenseClass) {
        return dao.delete(expenseClass);
    }
}
