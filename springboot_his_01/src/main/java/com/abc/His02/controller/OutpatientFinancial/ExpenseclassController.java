package com.abc.His02.controller.OutpatientFinancial;


import com.abc.His02.entity.ExpenseClass;
import com.abc.His02.service.OutpatientFinancial.IExpenseclassService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/expenseclass")
public class ExpenseclassController {
    @Autowired
    IExpenseclassService service;

    /**
     * 查询（可根据名字查询）
     */
    @RequestMapping("/getExpenseClassAll")
    public ResultUtil getExpenseClassAll(ExpenseClass expenseClass) {
        List<ExpenseClass> expenseClassList = service.selectAll(expenseClass);
        if (expenseClassList != null) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success", expenseClassList);
        }
        return new ResultUtil(EnumCode.GONE.getValue(), "查询不到");
    }

    /**
     * 增加
     */
    @RequestMapping("/addExpenseClass")
    public ResultUtil addExpenseClass(@RequestBody ExpenseClass expenseClass) {
        // 设置删除标记为正常
        expenseClass.setDelMark(1);

        if (service.add(expenseClass) == 1) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success");
        }

        return new ResultUtil(EnumCode.BAD_REQUEST.getValue(), "添加失败");
    }

    /**
     * 删除模板
     * 并非真正的删除
     * 而是将状态设置为已删除
     * 数据库中还存在这条数据
     */
    @RequestMapping("/deleteExpenseClass")
    public ResultUtil deleteExpenseClass(@RequestBody ExpenseClass expenseClass) {
        // 设置状态为已删除
        expenseClass.setDelMark(0);
        if (service.update(expenseClass) == 1) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success");
        }
        return new ResultUtil(EnumCode.BAD_REQUEST.getValue(), "删除失败");
    }

    /**
     * 修改模板
     */
    @RequestMapping("/updateExpenseClass")
    public ResultUtil updateExpenseClass(@RequestBody ExpenseClass expenseClass) {
        if (service.update(expenseClass) == 1) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success");
        }
        return new ResultUtil(EnumCode.BAD_REQUEST.getValue(), "修改失败");
    }
}
