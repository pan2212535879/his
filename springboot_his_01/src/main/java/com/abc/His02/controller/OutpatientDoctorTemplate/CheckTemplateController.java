package com.abc.His02.controller.OutpatientDoctorTemplate;

import com.abc.His02.entity.CheckTemplate;
import com.abc.His02.service.OutpatientDoctorTemplate.ICheckTemplateService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
public class CheckTemplateController {
    @Autowired
    private ICheckTemplateService service;

    /**
     * 模糊查询 UserID, RecordType, Name
     * 可随意组合以上三种参数搭配
     * 默认值（空字符串）获取全部模板
     */
    @RequestMapping("/getCheckTemplateAll")
    public ResultUtil getCheckTemplateAll(CheckTemplate checkTemplate) {
        List<CheckTemplate> checkTemplateList = service.selectAll(checkTemplate);
        if (checkTemplateList != null) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success", checkTemplateList);
        }
        return new ResultUtil(EnumCode.GONE.getValue(), "查询不到");
    }

    /**
     * 通过ID查询模板
     */
    @RequestMapping("/getCheckTemplateByID")
    public ResultUtil getCheckTemplateByID(CheckTemplate checkTemplate) {
        CheckTemplate param = service.selectById(checkTemplate);
        if (param != null) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success", param);
        }
        return new ResultUtil(EnumCode.GONE.getValue(), "查询不到");
    }

    /**
     * 增加模板
     */
    @RequestMapping("/addCheckTemplate")
    public ResultUtil addCheckTemplate(@RequestBody CheckTemplate checkTemplate) {
        // 设置当前时间为创建时间
        checkTemplate.setCreationTime(new Date());
        // 设置删除标记为正常
        checkTemplate.setDelMark(1);

        if (service.add(checkTemplate) == 1) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success");
        }

        return new ResultUtil(EnumCode.BAD_REQUEST.getValue(), "添加失败");
    }

    /**
     * 删除模板
     * 并非真正的删除
     * 而是将状态设置为已删除
     * 数据库中还存在这条数据
     */
    @RequestMapping("/deleteCheckTemplate")
    public ResultUtil deleteCheckTemplate(@RequestBody CheckTemplate checkTemplate) {
        System.out.println(checkTemplate);
        if (checkTemplate.getOperatorID().equals(checkTemplate.getUserID())) {
            // 设置状态为已删除
            checkTemplate.setDelMark(0);

            if (service.update(checkTemplate) == 1) {
                return new ResultUtil(EnumCode.OK.getValue(), "Success");
            }
            return new ResultUtil(EnumCode.BAD_REQUEST.getValue(), "删除失败");
        }

        return new ResultUtil(EnumCode.FORBIDDEN.getValue(), "删除失败,没有权限");
    }

    /**
     * 修改模板
     */
    @RequestMapping("/updateCheckTemplate")
    public ResultUtil updateCheckTemplate(@RequestBody CheckTemplate checkTemplate) {
        if (checkTemplate.getOperatorID().equals(checkTemplate.getUserID())) {
            if (service.update(checkTemplate) == 1) {
                return new ResultUtil(EnumCode.OK.getValue(), "Success");
            }
            return new ResultUtil(EnumCode.BAD_REQUEST.getValue(), "修改失败");
        }
        return new ResultUtil(EnumCode.FORBIDDEN.getValue(), "修改失败,没有权限");
    }
}
