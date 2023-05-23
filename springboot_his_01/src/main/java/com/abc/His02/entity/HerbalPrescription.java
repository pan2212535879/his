package com.abc.His02.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：患者草药处方表
public class HerbalPrescription {
    private Integer ID;// ID 自增长类型
    private Integer MedicalID;// 病历 ID 指向 MedicalRecord(ID)
    private Integer RegistID;// 挂号 ID 指向 Register(ID)
    private Integer DoctorID;// 开立医生 ID 指向 User (ID)
    private String PrescriptionName;// 处方名称

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date CreationTime;// 开立时间
    private String PrescriptioType;// 处方类型 水煎煮/酒泡/打粉/制丸/装胶囊等
    private Integer PayNumber;// 付数
    private String Frequency;// 频次
    private String DrugsUsage;// 用法 内服/外用
    private String Therapy;// 治法
    private String Detailed;// 治法详细
    private String Advice;// 医嘱
    private Integer State;// 状态 1-暂存 2-已开立 3-已交费 4-已取药 5-已退药 6-已退费 0-已作废

    @Override
    public String toString() {
        return "HerbalPrescription{" +
                "ID=" + ID +
                ", MedicalID=" + MedicalID +
                ", RegistID=" + RegistID +
                ", DoctorID=" + DoctorID +
                ", PrescriptionName='" + PrescriptionName + '\'' +
                ", CreationTime=" + CreationTime +
                ", PrescriptioType='" + PrescriptioType + '\'' +
                ", PayNumber=" + PayNumber +
                ", Frequency='" + Frequency + '\'' +
                ", DrugsUsage='" + DrugsUsage + '\'' +
                ", Therapy='" + Therapy + '\'' +
                ", Detailed='" + Detailed + '\'' +
                ", Advice='" + Advice + '\'' +
                ", State=" + State +
                '}';
    }
}
