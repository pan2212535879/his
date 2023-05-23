package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：疾病分类表
public class DiseCategory {
    private Integer ID;// ID 自增长类型
    private String DicaCode;// 分类编码
    private String DicaName;// 分类名称
    private Integer SequenceNo;// 显示顺序号
    private Integer DicaType;// 疾病类型 1-西医 2-中医
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "DiseCategory{" +
                "ID=" + ID +
                ", DicaCode='" + DicaCode + '\'' +
                ", DicaName='" + DicaName + '\'' +
                ", SequenceNo=" + SequenceNo +
                ", DicaType=" + DicaType +
                ", DelMark=" + DelMark +
                '}';
    }
}
