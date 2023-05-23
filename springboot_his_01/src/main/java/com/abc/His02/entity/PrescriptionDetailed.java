package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 表名：患者成药处方明细表*/
public class PrescriptionDetailed {
    private Integer ID;// ID 自增长类型
    private Integer PrescriptionID;// 成药处方ID 指向 Prescription(ID)
    private Integer DrugsID;// 药品 ID 指向 Drugs (ID)
    private String DrugsUsage;// 用法
    private String Dosage;// 用量
    private String Frequency;// 频次
    private BigDecimal Amount;// 数量
    private Integer State;// 状态 2-已开立 3-已交费 4-已发药 5-已退药 6-已退费

    @Override
    public String toString() {
        return "PrescriptionDetailed{" +
                "ID=" + ID +
                ", PrescriptionID=" + PrescriptionID +
                ", DrugsID=" + DrugsID +
                ", DrugsUsage='" + DrugsUsage + '\'' +
                ", Dosage='" + Dosage + '\'' +
                ", Frequency='" + Frequency + '\'' +
                ", Amount=" + Amount +
                ", State=" + State +
                '}';
    }
}
