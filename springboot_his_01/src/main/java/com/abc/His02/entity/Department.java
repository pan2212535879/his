package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：科室表
public class Department {
    private Integer ID;// ID 自增长类型
    private String DeptCode;// 科室编码 如：SJNK
    private String DeptName;// 科室名称 如：神经内科
    private Integer DeptCategoryID;// 科室分类 指向ConstantItem(ID)
    private Integer DeptType;// 科室类型 1-临床 2-医技 3-财务 4-行政 5-其它
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "Department{" +
                "ID=" + ID +
                ", DeptCode='" + DeptCode + '\'' +
                ", DeptName='" + DeptName + '\'' +
                ", DeptCategoryID=" + DeptCategoryID +
                ", DeptType=" + DeptType +
                ", DelMark=" + DelMark +
                '}';
    }
}
