package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：成药模板明细表
public class DrugsDetailed {
    private Integer ID;// ID 自增长类型
    private Integer DrugsTempID;// 成药模板ID 指向 DrugsTemplate(ID)
    private Integer DrugsID;// 药品 ID 指向 Drugs (ID)
    private String DrugsUsage;// 用法
    private String Dosage;// 用量
    private String Frequency;// 频次

    @Override
    public String toString() {
        return "DrugsDetailed{" +
                "ID=" + ID +
                ", DrugsTempID=" + DrugsTempID +
                ", DrugsID=" + DrugsID +
                ", DrugsUsage='" + DrugsUsage + '\'' +
                ", Dosage='" + Dosage + '\'' +
                ", Frequency='" + Frequency + '\'' +
                '}';
    }
}
