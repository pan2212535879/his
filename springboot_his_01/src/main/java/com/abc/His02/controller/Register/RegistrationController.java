package com.abc.His02.controller.Register;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.abc.His02.service.Register.RegisterService;
import com.abc.His02.entity.Invoice;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.abc.His02.entity.Register;

@RestController
@RequestMapping("/register_clerk")
public class RegistrationController {
	@Autowired
	private RegisterService service;

	@RequestMapping("/register")
	public ResultUtil add(Register r) {
		System.out.println("添加信息："+r.getRegistLeID());
		return new ResultUtil(EnumCode.OK.getValue(),"挂号成功",service.add(r));
	}
	@PostMapping("/selectAll")
	public ResultUtil selectAll(Register r) {
		System.out.println("查询执行了");
		return new ResultUtil(EnumCode.OK.getValue(), "查询成功",service.selectAll(r));
	}

	@PostMapping("/print")
	public ResultUtil print(@RequestBody Register r) throws ParseException {
		Invoice invoice1 = new Invoice();
		Invoice invoice2 = new Invoice();
		invoice1 = service.selectMaxInvoice();
		int invoicenum1 = Integer.parseInt(invoice1.getInvoiceNum());
		int invoicenum2 = invoicenum1 + 1;
		invoice2.setInvoiceNum(Integer.toString(invoicenum2));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date nowDate = df.parse(df.format(new Date()));
		invoice2.setCreationTime(nowDate);
		invoice2.setState(1);
		invoice2.setDailyState(0);
		invoice2.setFeeType(51);
		invoice2.setRegistID(r.getID());
		BigDecimal sumMoney = new BigDecimal("0");
		BigDecimal bignum1 = new BigDecimal("50");
		BigDecimal bignum2 = new BigDecimal("8");
		BigDecimal bignum3 = new BigDecimal("1");
		if(r.getIsBook().equals('1') == true){
			sumMoney = sumMoney.add(bignum3);
		}
		if(r.getRegistLeID() == 1){
			sumMoney = sumMoney.add(bignum1);
		}else if (r.getRegistLeID() == 2) {
			sumMoney = sumMoney.add(bignum2);
		}
		invoice2.setMoney(sumMoney);
		invoice2.setUserID(9);
		return new ResultUtil(EnumCode.OK.getValue(), "打印成功",service.print(r));
	}

	@RequestMapping("selectInvoice")
	public ResultUtil selectInvoice(Invoice invoice) {
		System.out.println("查询发票执行了");
		return new ResultUtil(EnumCode.OK.getValue(), "查询发票成功",service.selectInvoice(invoice));
	}

	@RequestMapping("selectMaxInvoice")
	public Invoice selectMaxInvoce() throws ParseException {
		Invoice invoice1 = new Invoice();
		invoice1 = service.selectMaxInvoice();
		Invoice invoice2 = new Invoice();
		invoice1 = service.selectMaxInvoice();
		int invoicenum1 = Integer.parseInt(invoice1.getInvoiceNum());
		int invoicenum2 = invoicenum1 + 1;
		invoice2.setInvoiceNum(Integer.toString(invoicenum2));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date nowDate = df.parse(df.format(new Date()));
		invoice2.setCreationTime(nowDate);
		return invoice1;
	}
}
