package com.abc.His02.controller.OutpatientFinancial;

import com.abc.His02.entity.OutpatientFinancial.DeptWorkload;
import com.abc.His02.entity.OutpatientFinancial.WorkloadStatistics;
import com.abc.His02.service.OutpatientFinancial.IWorkloadStatisticsService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/outpatientFinancial")
public class WorkloadStatisticsController {
    @Autowired
    IWorkloadStatisticsService service;

    @RequestMapping("/getWorkloadStatisticsAll")
    public ResultUtil getWorkloadStatisticsAll(WorkloadStatistics workloadStatistics) {
        List<WorkloadStatistics> workloadStatisticsList = service.selectAll(workloadStatistics);
        if (workloadStatisticsList != null) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success", workloadStatisticsList);
        }
        return new ResultUtil(EnumCode.GONE.getValue(), "查询不到");
    }

    @RequestMapping("/getDepartmentAll")
    public ResultUtil getDepartmentAll (DeptWorkload deptWorkload){
        List<DeptWorkload> departmentList = service.selectDeptList(deptWorkload);

        System.out.println(departmentList);

        if (departmentList != null) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success", departmentList);
        }
        return new ResultUtil(EnumCode.GONE.getValue(), "查询不到");
    }

}
