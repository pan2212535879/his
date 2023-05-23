package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：草药模板明细表
public class HerbalTempDetailed {
    private Integer ID;// ID 自增长类型
    private Integer HerbalTempID;// 草药模板ID 指向 HerbalTemplate(ID)
    private Integer HerbalID;// 药品 ID 指向 Drugs (ID)
    private String Dosage;// 用量
    private String Unit;// 药品单位
    private String Footnote;// 脚注

    @Override
    public String toString() {
        return "HerbalTempDetailed{" +
                "ID=" + ID +
                ", HerbalTempID=" + HerbalTempID +
                ", HerbalID=" + HerbalID +
                ", Dosage='" + Dosage + '\'' +
                ", Unit='" + Unit + '\'' +
                ", Footnote='" + Footnote + '\'' +
                '}';
    }
}
