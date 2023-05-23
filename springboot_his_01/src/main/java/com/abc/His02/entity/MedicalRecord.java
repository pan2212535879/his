package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：患者病历首页表
public class MedicalRecord {
    private Integer ID;// ID 自增长类型
    private String CaseNumber;// 病历号
    private Integer RegistID;// 挂号 ID 指向 Register(ID)
    private String Readme;// 主诉
    private String Present;// 现病史
    private String PresentTreat;// 现病治疗情况
    private String History;// 既往史
    private String Allergy;// 过敏史
    private String Physique;// 体格检查
    private String Proposal;// 检查建议
    private String Careful;// 注意事项
    private String CheckResult;// 检查结果
    private String Diagnosis;// 诊断结果
    private String Handling;// 处理意见
    private Integer CaseState;// 病历状态 1-暂存 2-已提交 3-诊毕
    @Override
    public String toString() {
        return "MedicalRecord{" +
                "ID=" + ID +
                ", CaseNumber='" + CaseNumber + '\'' +
                ", RegistID=" + RegistID +
                ", Readme='" + Readme + '\'' +
                ", Present='" + Present + '\'' +
                ", PresentTreat='" + PresentTreat + '\'' +
                ", History='" + History + '\'' +
                ", Allergy='" + Allergy + '\'' +
                ", Physique='" + Physique + '\'' +
                ", Proposal='" + Proposal + '\'' +
                ", Careful='" + Careful + '\'' +
                ", CheckResult='" + CheckResult + '\'' +
                ", Diagnosis='" + Diagnosis + '\'' +
                ", Handling='" + Handling + '\'' +
                ", CaseState=" + CaseState +
                '}';
    }
}
