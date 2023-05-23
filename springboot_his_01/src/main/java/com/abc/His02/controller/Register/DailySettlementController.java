package com.abc.His02.controller.Register;

import com.abc.His02.entity.Invoice;
import com.abc.His02.service.Register.DailySettlementService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/daily")
public class DailySettlementController {
    @Autowired
    private DailySettlementService service;

    @RequestMapping("/selectBydate")
    // http://localhost:8899/daily/selectBydate?CreationTime=2021-12-15 10:25:23
    public ResultUtil selectBydate(Invoice invoice) throws ParseException {
        System.out.println("开始时间"+invoice.getCreationTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date nowDate = df.parse(df.format(new Date()));
        return new ResultUtil(EnumCode.OK.getValue(), "查询成功",service.selectAllInvoice(invoice.getCreationTime(),nowDate));
    }

    @PostMapping("/updateInvoice")
    public ResultUtil updateInvoice(@RequestBody Invoice invoice){
        System.out.println("修改执行");
        System.out.println(invoice.getID());
        service.updateInvoice(invoice);
        return new ResultUtil(EnumCode.OK.getValue(), "修改成功",1);
    }
}
