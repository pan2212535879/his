package com.abc.His02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 表名：检查/检验/处置模板明细表*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRelation {
    private Integer ID;// ID 自增长类型
    private Integer CheckProjID;// 项目 ID 指向 Fmeditem(ID)
    private Integer CheckTempID;// 申请模板ID 指向 CheckTemplate(ID)
    private String Position;// 检查部位

    @Override
    public String toString() {
        return "CheckRelation{" +
                "ID=" + ID +
                ", CheckProjID=" + CheckProjID +
                ", CheckTempID=" + CheckTempID +
                ", Position='" + Position + '\'' +
                '}';
    }
}
