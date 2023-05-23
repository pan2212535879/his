package com.abc.His02.controller.outpatientPharmacyWorkstationController;

import com.abc.His02.entity.Prescription;
import com.abc.His02.service.outpatientPharmacyWorkstationService.OutpatientDispensingService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/outpatientPharmacyWorkstation/outpatientDispensing")
public class OutpatientDispensingController{
	@Autowired
	private OutpatientDispensingService service;

	@RequestMapping("/selectAll")
	public ResultUtil selectAll(Prescription u){
		System.out.println(u);
		List<Prescription> list = service.selectAll(u);
		if(list != null){
			System.out.println("执行了查询语句");
			return  new ResultUtil(EnumCode.OK.getValue(),"查询成功",service.selectAll(u));
		}
		return new ResultUtil(EnumCode.UNAUTHORIZED.getValue(), "查询失败");
	}



	@RequestMapping("/update")
	public ResultUtil update(@RequestParam String id) {
		System.out.println("---u="+id);
		int res = service.update(id);
		if(res > 0){
			System.out.println("执行了修改语句");
			return  new ResultUtil(EnumCode.OK.getValue(),"取药成功");
		}
		return new ResultUtil(EnumCode.UNAUTHORIZED.getValue(), "取药失败");
	}



}
