package com.abc.His02.entity.OutpatientFinancial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkloadStatistics {
    private Integer ID;
    private String Name;
    private float Price;
    private float Amount;
    private Integer DeptID;
    private String DeptName;
    private String DeptType;
    private Integer CreateOperID;
    private Integer ExpID;
    private String ExpName;
    private Integer DrugsID;
    private String ConstantName;
    private String StartTime;
    private String EndTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date Createtime;

    @Override
    public String toString() {
        return "WorkloadStatistics{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                ", Amount=" + Amount +
                ", DeptID=" + DeptID +
                ", DeptName='" + DeptName + '\'' +
                ", DeptType='" + DeptType + '\'' +
                ", CreateOperID=" + CreateOperID +
                ", ExpID=" + ExpID +
                ", ExpName='" + ExpName + '\'' +
                ", DrugsID=" + DrugsID +
                ", ConstantName='" + ConstantName + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", Createtime=" + Createtime +
                '}';
    }
}
