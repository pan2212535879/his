package com.abc.His02.service.OutpatientFinancial;

import com.abc.His02.entity.OutpatientFinancial.DeptWorkload;
import com.abc.His02.entity.OutpatientFinancial.WorkloadStatistics;

import java.util.List;

public interface IWorkloadStatisticsService {
    List<WorkloadStatistics> selectAll(WorkloadStatistics workloadStatistics);
    List<DeptWorkload> selectDeptList(DeptWorkload department);
}
