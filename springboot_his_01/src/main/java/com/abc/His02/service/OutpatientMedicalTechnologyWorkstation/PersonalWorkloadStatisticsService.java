package com.abc.His02.service.OutpatientMedicalTechnologyWorkstation;


import com.abc.His02.entity.CheckApply;

import java.util.List;

public interface PersonalWorkloadStatisticsService {
    List<CheckApply> selectAll(CheckApply u);
    CheckApply selectByID();
}
