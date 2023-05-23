package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 表名：检查/检验/处置申请模板表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckTemplate {
    private Integer ID;// ID 自增长类型
    private String Name;// 名称
    private Integer UserID;// 医生 ID 指向 User (ID)
    /**
     * OperatorID这个变量用于比对UserID
     * 防止非模创建者对模板操作（删，改）
     * 该变量不会存储到数据库中
     */
    private Integer OperatorID;// 操作员ID

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date CreationTime;// 创建时间
    private String Scope;// 使用范围 全院/科室/个人
    private Integer RecordType;// 记录类型 1-检查 2-检验 3-处置
    private Integer DelMark;// 删除标记 1-正常 0-已删除


    @Override
    public String toString() {
        return "CheckTemplate{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", UserID=" + UserID +
                ", CreationTime=" + CreationTime +
                ", Scope='" + Scope + '\'' +
                ", RecordType=" + RecordType +
                ", DelMark=" + DelMark +
                ", OperatorID=" + OperatorID +
                '}';
    }
}
