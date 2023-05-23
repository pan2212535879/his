package com.abc.His02.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 表名：成药处方模板表
public class DrugsTemplate {
    private Integer ID;// ID 自增长类型
    private String Name;// 名称
    private Integer UserID;// 医生 ID 指向 User (ID)

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date CreationTime;// 创建时间
    private String Scope;// 使用范围 全院/科室/个人
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "DrugsTemplate{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", UserID=" + UserID +
                ", CreationTime=" + CreationTime +
                ", Scope='" + Scope + '\'' +
                ", DelMark=" + DelMark +
                '}';
    }
}
