package com.abc.His02.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 排班表
public class Scheduling {
    private Integer ID;// ID 自增长类型

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date SchedDate;// 排班日期
    private Integer DeptID;// 科室 ID 指向 Department( ID )
    private Integer UserID;// 医生 ID 指向 User (ID)
    private String Noon;// 午别 上午/下午
    private Integer RuleID;// 排班规则 ID 指向 Rule ( ID )
    @Override
    public String toString() {
        return "Scheduling{" +
                "ID=" + ID +
                ", SchedDate=" + SchedDate +
                ", DeptID=" + DeptID +
                ", UserID=" + UserID +
                ", Noon='" + Noon + '\'' +
                ", RuleID=" + RuleID +
                '}';
    }
}
