package com.abc.His02.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：患者评估/诊断表
public class MedicalDisease {
    private Integer ID;// ID 自增长类型
    private Integer MedicalID;// 病历 ID 指向 MedicalRecord(ID)
    private Integer RegistID;// 挂号 ID 指向 Register(ID)
    private Integer DiseaseID;// 疾病 ID 指向 Disease(ID)
    private Integer DiagnoseType;// 诊断类型 1-西医 2-中医

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date GetSiskDate;// 发病日期
    private Integer DiagnoseCate;// 诊断种类 1-初诊 2-终诊

    @Override
    public String toString() {
        return "MedicalDisease{" +
                "ID=" + ID +
                ", MedicalID=" + MedicalID +
                ", RegistID=" + RegistID +
                ", DiseaseID=" + DiseaseID +
                ", DiagnoseType=" + DiagnoseType +
                ", GetSiskDate=" + GetSiskDate +
                ", DiagnoseCate=" + DiagnoseCate +
                '}';
    }
}
