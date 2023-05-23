package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：疾病表
public class Disease {
    private Integer ID;// ID 自增长类型
    private String DiseaseCode;// 疾病助记编码
    private String DiseaseName;// 疾病名称
    private String DiseaseICD;// 国际 ICD 编码
    private Integer DiseCategoryID;// 疾病所属分类 指向DiseCategory(ID)
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "Disease{" +
                "ID=" + ID +
                ", DiseaseCode='" + DiseaseCode + '\'' +
                ", DiseaseName='" + DiseaseName + '\'' +
                ", DiseaseICD='" + DiseaseICD + '\'' +
                ", DiseCategoryID=" + DiseCategoryID +
                ", DelMark=" + DelMark +
                '}';
    }
}
