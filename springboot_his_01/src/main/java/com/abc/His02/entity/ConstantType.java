package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：常数类别表
public class ConstantType {
    private Integer ID;// ID 自增长类型
    private String ConstantTypeCode;// 常数类别编码 如DeptType
    private String ConstantTypeName;// 常数类别名称 如性别
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "ConstantType{" +
                "ID=" + ID +
                ", ConstantTypeCode='" + ConstantTypeCode + '\'' +
                ", ConstantTypeName='" + ConstantTypeName + '\'' +
                ", DelMark=" + DelMark +
                '}';
    }
}
