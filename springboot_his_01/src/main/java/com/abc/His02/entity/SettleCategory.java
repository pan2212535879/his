package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：结算类别表
public class SettleCategory {
    private Integer ID;// ID 自增长类型
    private String SettleCode;// 类别编码
    private String SettleName;// 类别名称
    private Integer SequenceNo;// 显示顺序号
    private Integer DelMark;// 删除标记 1-正常 0-已删除


    @Override
    public String toString() {
        return "SettleCategory{" +
                "ID=" + ID +
                ", SettleCode='" + SettleCode + '\'' +
                ", SettleName='" + SettleName + '\'' +
                ", SequenceNo=" + SequenceNo +
                ", DelMark=" + DelMark +
                '}';
    }
}
