package com.abc.His02.controller.outpatientPharmacyWorkstationController;

import com.abc.His02.entity.Prescription;
import com.abc.His02.service.outpatientPharmacyWorkstationService.OutpatientWithdrawalService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/outpatientPharmacyWorkstation/outpatientWithdrawal")
public class OutpatientWithdrawalController {
@Autowired
private OutpatientWithdrawalService service;

	@RequestMapping("/selectAll")
	public ResultUtil selectAll(Prescription u){
		System.out.println(u);
		List<Prescription> list = service.selectAll(u);
		if(list != null){
			System.out.println("执行了查询语句");
			return  new ResultUtil(EnumCode.OK.getValue(),"查询成功",list);
		}
		return new ResultUtil(EnumCode.UNAUTHORIZED.getValue(), "查询失败");
	}



	@RequestMapping("/update")
	public ResultUtil update(@RequestParam String id,@RequestParam String amount) {
		System.out.println("---u="+id+amount);
		int res = service.update(id,amount);
		if(res > 0){
			System.out.println("执行了修改语句");
			return  new ResultUtil(EnumCode.OK.getValue(),"退药成功");
		}
		return new ResultUtil(EnumCode.UNAUTHORIZED.getValue(), "退药失败");
	}

	@RequestMapping("/updateAll")
	public ResultUtil updateAll(@RequestParam String caseNumber) {
		System.out.println("---u="+caseNumber);
		Prescription res = service.updateAll(caseNumber);
		System.out.println(res);
		if(res != null){
			System.out.println("执行了修改语句");
			return  new ResultUtil(EnumCode.OK.getValue(),"退药成功",res);
		}
		return new ResultUtil(EnumCode.UNAUTHORIZED.getValue(), "退药失败");
	}



}
