package com.abc.His02.mapper.OutpatientFinancial;

import com.abc.His02.entity.OutpatientFinancial.DeptWorkload;
import com.abc.His02.entity.OutpatientFinancial.WorkloadStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface WorkloadStatisticsMapper {
    List<WorkloadStatistics> selectAll(WorkloadStatistics workloadStatistics);
    List<DeptWorkload> selectDeptList(DeptWorkload department);
}
