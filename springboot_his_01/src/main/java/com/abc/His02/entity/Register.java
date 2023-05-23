package com.abc.His02.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 患者表
 * */
public class Register {
    private Integer ID;// ID 自增长类型
    private String CaseNumber;// 病历号
    private String RealName;// 姓名
    private Integer Gender;// 性别
    private String IDnumber;// 身份证号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date BirthDate;// 出生日期
    private Integer Age;// 年龄
    private String AgeType;// 年龄类型 岁/月/天
    private String HomeAddress;// 家庭住址
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date VisitDate;// 本次看诊日期
    private String Noon;// 午别 上午/下午
    private Integer DeptID;// 本次挂号科室ID 指向 Department( ID )
    private Integer UserID;// 本次挂号医生ID 指向 User (ID)
    private Integer RegistLeID;// 本次挂号级别ID 指向 RegistLevel(ID)
    private Integer SettleID;// 结算类别 ID 指向SettleCategory(ID)
    private String IsBook;// 病历本要否 是/否
    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date RegistTime;// 挂号时间
    private Integer RegisterID;// 挂号员 ID 指向 User(ID)
    private Integer VisitState;// 本次看诊状态 1-已挂号 2-医生接诊 3-看诊结束 4-已退号
    private String StartTime;
    private String EndTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getBirthDate() {
        return BirthDate;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getVisitDate() {
        return VisitDate;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getRegistTime() {
        return RegistTime;
    }
}
