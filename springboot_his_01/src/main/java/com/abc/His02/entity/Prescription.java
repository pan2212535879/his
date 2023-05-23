package com.abc.His02.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：患者成药处方表
public class Prescription {
    private Integer CaseNumber;
    private String RealName;
    private String Gender;
    private Integer Age;
    // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date PayTime;
    private String DrugsCode;// 药品编码
    private String DrugsName;// 药品名称
    private String DrugsFormat;// 药品规格
    private String DrugsUnit;// 包装单位
    private String Manufacturer;// 生产厂家
    private Integer DrugsDosageID;// 药品剂型 指向ConstatntItem（ID）
    private Integer DrugsTypeID;// 药品类型 指向ConstatntItem（ID）
    private BigDecimal DrugsPrice;// 药品单价
    private String MnemonicCode;// 拼音助记码
    private Integer ID;// ID 自增长类型
    private Integer SUM;
    private Integer MedicalID;// 病历 ID 指向 MedicalRecord(ID)
    private Integer RegistID;// 挂号 ID 指向 Register(ID)
    private Integer UserID;// 开立医生 ID 指向 User (ID)
    private String PrescriptionName;// 处方名称
    private Integer PrescriptionID;// 成药处方ID 指向 Prescription(ID)
    private Integer DrugsID;// 药品 ID 指向 Drugs (ID)
    private String DrugsUsage;// 用法
    private String Dosage;// 用量
    private String Frequency;// 频次
    private BigDecimal Amount;// 数量
    private Integer State;// 状态 2-已开立 3-已交费 4-已发药 5-已退药 6-已退费

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date PrescriptionTime;// 开立时间
    private Integer PrescriptionState;// 处方状态 1-暂存 2-已开立 0-已作废

    @Override
    public String toString() {
        return "Prescription{" +
                "CaseNumber=" + CaseNumber +
                ", RealName='" + RealName + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Age=" + Age +
                ", PayTime=" + PayTime +
                ", DrugsCode='" + DrugsCode + '\'' +
                ", DrugsName='" + DrugsName + '\'' +
                ", DrugsFormat='" + DrugsFormat + '\'' +
                ", DrugsUnit='" + DrugsUnit + '\'' +
                ", Manufacturer='" + Manufacturer + '\'' +
                ", DrugsDosageID=" + DrugsDosageID +
                ", DrugsTypeID=" + DrugsTypeID +
                ", DrugsPrice=" + DrugsPrice +
                ", MnemonicCode='" + MnemonicCode + '\'' +
                ", ID=" + ID +
                ", SUM=" + SUM +
                ", MedicalID=" + MedicalID +
                ", RegistID=" + RegistID +
                ", UserID=" + UserID +
                ", PrescriptionName='" + PrescriptionName + '\'' +
                ", PrescriptionID=" + PrescriptionID +
                ", DrugsID=" + DrugsID +
                ", DrugsUsage='" + DrugsUsage + '\'' +
                ", Dosage='" + Dosage + '\'' +
                ", Frequency='" + Frequency + '\'' +
                ", Amount=" + Amount +
                ", State=" + State +
                ", PrescriptionTime=" + PrescriptionTime +
                ", PrescriptionState=" + PrescriptionState +
                '}';
    }
}
