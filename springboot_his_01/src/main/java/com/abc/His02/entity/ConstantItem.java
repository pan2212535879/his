package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 表名：常数项表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstantItem {
    private Integer ID;// ID 自增长类型
    private Integer ConstantTypeID;// 所属常数类别ID 指向ConstantType( ID )
    private String ConstantCode;// 常数项编码
    private String ConstantName;// 常数项名称 如男、女等
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "ConstantItem{" +
                "ID=" + ID +
                ", ConstantTypeID=" + ConstantTypeID +
                ", ConstantCode='" + ConstantCode + '\'' +
                ", ConstantName='" + ConstantName + '\'' +
                ", DelMark=" + DelMark +
                '}';
    }
}
