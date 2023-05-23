package com.abc.His02.controller.OutpatientDoctorTemplate;

import com.abc.His02.entity.PatientCosts;
import com.abc.His02.service.OutpatientDoctorTemplate.IPatientcostsService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
public class PatientcostsController {
    @Autowired
    IPatientcostsService service;

    @RequestMapping("/getPatientCostAll")
    public ResultUtil getCheckTemplateAll(PatientCosts patientCosts) {
        List<PatientCosts> patientCostsList = service.selectAll(patientCosts);
        if (patientCostsList != null) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success", patientCostsList);
        }
        return new ResultUtil(EnumCode.GONE.getValue(), "查询不到");
    }

}
