package com.abc.His02.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：草药处方模板表
public class HerbalTemplate {
    private Integer ID;// ID 自增长类型
    private String Name;// 名称
    private Integer DoctorID;// 医生 ID 指向 User (ID)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date CreationTime;// 创建时间
    private String PrescriptioType;// 处方类型 水煎煮/酒泡/打粉/制丸/装胶囊等
    private Integer PayNumber;// 付数
    private String DrugsUsage;// 用法
    private String Therapy;// 治法
    private String Detailed;// 治法详细
    private String Advice;// 医嘱
    private String Scope;// 使用范围 全院/科室/个人
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "HerbalTemplate{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", DoctorID=" + DoctorID +
                ", CreationTime=" + CreationTime +
                ", PrescriptioType='" + PrescriptioType + '\'' +
                ", PayNumber=" + PayNumber +
                ", DrugsUsage='" + DrugsUsage + '\'' +
                ", Therapy='" + Therapy + '\'' +
                ", Detailed='" + Detailed + '\'' +
                ", Advice='" + Advice + '\'' +
                ", Scope='" + Scope + '\'' +
                ", DelMark=" + DelMark +
                '}';
    }
}
