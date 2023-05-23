package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 表名：用户表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer ID;// ID 自增长类型
    private String UserName;// 登录名
    private String Password;// 密码
    private String RealName;// 真实姓名
    private Integer UseType;// 用户类别 1-医院管理员 2-挂号收费员 3-门诊医生 4-医技医生 5-药房操作员 6-财务管理员 7-患者
    private Integer DocTitleID;// 医生职称 ID 指向ConstantItem（ID）
    private String IsScheduling;// 是否参与排班 是/否
    private Integer DeptID;// 所在科室 ID 指向Department( ID )
    private Integer RegistLeID;// 挂号级别 ID 指向RegistLevel(ID)
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", RealName='" + RealName + '\'' +
                ", UseType=" + UseType +
                ", DocTitleID=" + DocTitleID +
                ", IsScheduling='" + IsScheduling + '\'' +
                ", DeptID=" + DeptID +
                ", RegistLeID=" + RegistLeID +
                ", DelMark=" + DelMark +
                '}';
    }

}
