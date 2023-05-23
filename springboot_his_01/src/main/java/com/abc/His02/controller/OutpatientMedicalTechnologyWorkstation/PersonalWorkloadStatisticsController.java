package com.abc.His02.controller.OutpatientMedicalTechnologyWorkstation;

import com.abc.His02.entity.CheckApply;
import com.abc.His02.service.OutpatientMedicalTechnologyWorkstation.PersonalWorkloadStatisticsService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/outpatientMedicalTechnologyWorkstation/personalWorkloadStatistics")
public class PersonalWorkloadStatisticsController {
    @Autowired
    private PersonalWorkloadStatisticsService service;

    @RequestMapping("/selectAll")
    public ResultUtil selectAll(CheckApply u){
        System.out.println(u);
        List<CheckApply> list = service.selectAll(u);
        if(list != null){
            System.out.println("执行了查询语句");
            return  new ResultUtil(EnumCode.OK.getValue(),"查询成功",service.selectAll(u));
        }
        return new ResultUtil(EnumCode.UNAUTHORIZED.getValue(), "查询失败");
    }

    @RequestMapping("/selectByID")
    public ResultUtil selectAll(){
        CheckApply res = service.selectByID();
        if(res != null){
            System.out.println("执行了查询语句");
            return  new ResultUtil(EnumCode.OK.getValue(),"刷新成功",service.selectByID());
        }
        return new ResultUtil(EnumCode.UNAUTHORIZED.getValue(), "查询失败");
    }
}
