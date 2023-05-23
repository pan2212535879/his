package com.abc.His02.service.OutpatientFinancial;

import com.abc.His02.entity.OutpatientFinancial.DeptWorkload;
import com.abc.His02.entity.OutpatientFinancial.WorkloadStatistics;
import com.abc.His02.mapper.OutpatientFinancial.WorkloadStatisticsMapper;
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
public class WorkloadStatisticsImpl implements IWorkloadStatisticsService{
    @Autowired
    WorkloadStatisticsMapper dao;

    @Override
    public List<WorkloadStatistics> selectAll(WorkloadStatistics workloadStatistics) {
        return dao.selectAll(workloadStatistics);
    }

    @Override
    public List<DeptWorkload> selectDeptList(DeptWorkload department) {
        return dao.selectDeptList(department);
    }

}
