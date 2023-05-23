package com.abc.His02.controller.OutpatientMedicalTechnologyWorkstation;

import com.abc.His02.entity.CheckApply;
import com.abc.His02.service.OutpatientMedicalTechnologyWorkstation.InspectionAndTestRegisterService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InspectionAndTestRegisterController {

       @Autowired
        private InspectionAndTestRegisterService service;

        /**
         * 模糊查询 UserID, RecordType, Name
         * 可随意组合以上三种参数搭配
         * 默认值（空字符串）获取全部模板
         */
        @RequestMapping("/getCheckApplyAll")
        public ResultUtil getCheckApplyAll(CheckApply CheckApply) {
            List<CheckApply> CheckApplyList = service.selectAll(CheckApply);
            if (CheckApplyList != null) {
                return new ResultUtil(EnumCode.OK.getValue(), "Success", CheckApplyList);
            }
            return new ResultUtil(EnumCode.GONE.getValue(), "查询不到");
        }

        @RequestMapping("/updateCheckApplyState")
        public ResultUtil updateCheckApply(CheckApply CheckApply) {
            System.out.println(CheckApply);
            int i  = service.updateState(CheckApply);
            if (i != 0) {
                return new ResultUtil(EnumCode.OK.getValue(), "Success", i);
            }
            return new ResultUtil(EnumCode.GONE.getValue(), "查询不到");
        }
}
