package com.abc.His02.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：操作员日结表
public class RegistWork {
    private Integer ID;// ID 自增长类型
    private Integer RegisterID; // 收费人员 ID 指向 User (ID)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date StartTime;// 日结起始时间

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date EndTime;// 日结结束时间

    @Override
    public String toString() {
        return "RegistWork{" +
                "ID=" + ID +
                ", RegisterID=" + RegisterID +
                ", StartTime=" + StartTime +
                ", EndTime=" + EndTime +
                '}';
    }
}
