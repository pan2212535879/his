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
// 表名：患者费用明细表
public class PatientCosts {
    private String CaseNumber;
    private String RealName;
    private String DeptName;
    private String StartTime;
    private String EndTime;
    private Integer ID;// ID 自增长类型
    private Integer RegistID;// 挂号 ID 指向 Register (ID)
    private Integer InvoiceID;// 发票 ID 指向 invoice(ID)
    private Integer ItemID;// 项目 ID
    private Integer ItemType;// 项目类型 1-非药品 2-药品
    private String Name;// 项目名称
    private BigDecimal Price;// 项目单价
    private BigDecimal Amount;// 数量
    private Integer DeptID;// 执行科室ID
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date Createtime;// 开立时间
    private Integer CreateOperID;// 开立人员ID
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date PayTime;// 收/退费时间
    private Integer RegisterID;// 收/退费人员 ID
    private Integer FeeType;// 收费方式
    private Integer BackID;// 退费对应记录 ID


    public Date getCreatetime() {
        return Createtime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setCreatetime(Date createtime) {
        Createtime = createtime;
    }

    public Date getPayTime() {
        return PayTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setPayTime(Date payTime) {
        PayTime = payTime;
    }

    @Override
    public String toString() {
        return "PatientCosts{" +
                "CaseNumber='" + CaseNumber + '\'' +
                ", RealName='" + RealName + '\'' +
                ", DeptName='" + DeptName + '\'' +
                ", StartTime=" + StartTime +
                ", EndTime=" + EndTime +
                ", ID=" + ID +
                ", RegistID=" + RegistID +
                ", InvoiceID=" + InvoiceID +
                ", ItemID=" + ItemID +
                ", ItemType=" + ItemType +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                ", Amount=" + Amount +
                ", DeptID=" + DeptID +
                ", Createtime=" + Createtime +
                ", CreateOperID=" + CreateOperID +
                ", PayTime=" + PayTime +
                ", RegisterID=" + RegisterID +
                ", FeeType=" + FeeType +
                ", BackID=" + BackID +
                '}';
    }
}
