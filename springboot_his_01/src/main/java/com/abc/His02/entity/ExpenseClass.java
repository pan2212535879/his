package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：费用科目表
public class ExpenseClass {
    private Integer ID;// ID 自增长类型
    private String ExpCode;// 费用科目编码
    private String ExpName;// 费用科目名称
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "ExpenseClass{" +
                "ID=" + ID +
                ", ExpCode='" + ExpCode + '\'' +
                ", ExpName='" + ExpName + '\'' +
                ", DelMark=" + DelMark +
                '}';
    }
}
