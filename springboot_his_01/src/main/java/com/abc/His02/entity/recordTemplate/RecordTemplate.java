package com.abc.His02.entity.recordTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 */ // 表名：患者病历模板表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordTemplate {
    private Integer ID;// ID 自增长类型
    private String Name;// 名称
    private Integer DeptID;
    private Integer UserID;// 医生 ID 指向 User (ID)

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date CreationTime;// 创建时间
    private String Scope;// 使用范围 全院/科室/个人

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

    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "RecordTemplate{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", DeptID=" + DeptID +
                ", UserID=" + UserID +
                ", CreationTime=" + CreationTime +
                ", Scope='" + Scope + '\'' +
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
                ", DelMark=" + DelMark +
                '}';
    }
}
