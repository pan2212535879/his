package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：草药处方明细表
public class HerbalDetailed {
    private Integer ID;// ID 自增长类型
    private Integer HerbalPresID;// 草药处方ID 指向HerbalPrescription(ID)
    private Integer HerbalID;// 药品 ID 指向 Drugs (ID)
    private String Dosage;// 用量
    private BigDecimal Price;// 药品单价
    private String Footnote;// 脚注

    @Override
    public String toString() {
        return "HerbalDetailed{" +
                "ID=" + ID +
                ", HerbalPresID=" + HerbalPresID +
                ", HerbalID=" + HerbalID +
                ", Dosage='" + Dosage + '\'' +
                ", Price=" + Price +
                ", Footnote='" + Footnote + '\'' +
                '}';
    }
}
