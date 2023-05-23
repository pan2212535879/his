package com.abc.His02.entity.diagnosisTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 表名：医生诊断模板表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisTemplate {
    private Integer ID;// ID 自增长类型
    private Integer DeptID;
    private Integer UserID;// 医生 ID 指向 User (ID)
    private String Diagnosis;// 诊断结果
    private String Handling;// 处理意见
    private String Scope;// 使用范围 全院/科室/个人
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "DiagnosisTemplate{" +
                "ID=" + ID +
                ", DeptID=" + DeptID +
                ", UserID=" + UserID +
                ", Diagnosis='" + Diagnosis + '\'' +
                ", Handling='" + Handling + '\'' +
                ", Scope='" + Scope + '\'' +
                ", DelMark=" + DelMark +
                '}';
    }
}
