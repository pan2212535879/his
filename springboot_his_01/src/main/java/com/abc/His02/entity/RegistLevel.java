package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 表名：挂号级别表*/
public class RegistLevel {
    private Integer ID;// ID 自增长类型
    private String RegistCode;// 号别编码
    private String RegistName;// 号别名称
    private Integer SequenceNo;// 显示顺序号
    private BigDecimal RegistFee;// 挂号费
    private Integer RegistQuota;// 挂号限额
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "RegistLevel{" +
                "ID=" + ID +
                ", RegistCode='" + RegistCode + '\'' +
                ", RegistName='" + RegistName + '\'' +
                ", SequenceNo=" + SequenceNo +
                ", RegistFee=" + RegistFee +
                ", RegistQuota=" + RegistQuota +
                ", DelMark=" + DelMark +
                '}';
    }
}
