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
// 表名：发票表
public class Invoice {
    private Integer ID;// ID 自增长类型
    private String InvoiceNum;// 发票号码
    private BigDecimal Money;// 发票金额
    private Integer State;// 发票状态 1-正常 2-作废 3-未打印 4-已打印 5-已重打 6-已补打 7-冲红

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date CreationTime;// 收/退费时间
    private Integer UserID;// 收/退费人员 ID 指向 User (ID)
    private Integer RegistID;// 挂号 ID 指向 Register (ID)
    private Integer FeeType;// 收费方式 指向ConstantItem(ID)
    private String Back;// 冲红发票号码
    private Integer DailyState;// 日结审核状态 0-未日结 1-已提交 2-已审核

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreationTime() {
        return CreationTime;
    }

    public void setCreationTime(Date creationTime) {
        CreationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "ID=" + ID +
                ", InvoiceNum='" + InvoiceNum + '\'' +
                ", Money=" + Money +
                ", State=" + State +
                ", CreationTime=" + CreationTime +
                ", UserID=" + UserID +
                ", RegistID=" + RegistID +
                ", FeeType=" + FeeType +
                ", Back='" + Back + '\'' +
                ", DailyState=" + DailyState +
                '}';
    }
}
