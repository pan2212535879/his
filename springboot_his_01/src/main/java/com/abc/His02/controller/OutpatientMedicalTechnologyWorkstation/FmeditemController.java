package com.abc.His02.controller.OutpatientMedicalTechnologyWorkstation;


import com.abc.His02.entity.Fmeditem;
import com.abc.His02.service.OutpatientMedicalTechnologyWorkstation.FmeditemService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FmeditemController {

    @Autowired
    private FmeditemService fmeditemService;

    @RequestMapping("/getFmeditemAll")
    public ResultUtil getCheckTemplateAll(Fmeditem fmeditem) {
        List<Fmeditem> fmeditemList = fmeditemService.selectAll(fmeditem);
        if (fmeditemList != null) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success", fmeditemList);
        }
        return new ResultUtil(EnumCode.GONE.getValue(), "查询不到");
    }
}
