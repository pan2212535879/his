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
// 表名：药品目录表
public class Drugs {
    private Integer ID;// ID 自增长类型
    private String DrugsCode;// 药品编码
    private String DrugsName;// 药品名称
    private String DrugsFormat;// 药品规格
    private String DrugsUnit;// 包装单位
    private String Manufacturer;// 生产厂家
    private Integer DrugsDosageID;// 药品剂型 指向ConstatntItem（ID）
    private Integer DrugsTypeID;// 药品类型 指向ConstatntItem（ID）
    private BigDecimal DrugsPrice;// 药品单价
    private String MnemonicCode;// 拼音助记码

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date CreationDate;// 创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date LastUpdateDate;// 最后修改时间
    private Integer DelMark;// 删除标记 1-正常 0-已删除

    @Override
    public String toString() {
        return "Drugs{" +
                "ID=" + ID +
                ", DrugsCode='" + DrugsCode + '\'' +
                ", DrugsName='" + DrugsName + '\'' +
                ", DrugsFormat='" + DrugsFormat + '\'' +
                ", DrugsUnit='" + DrugsUnit + '\'' +
                ", Manufacturer='" + Manufacturer + '\'' +
                ", DrugsDosageID=" + DrugsDosageID +
                ", DrugsTypeID=" + DrugsTypeID +
                ", DrugsPrice=" + DrugsPrice +
                ", MnemonicCode='" + MnemonicCode + '\'' +
                ", CreationDate=" + CreationDate +
                ", LastUpdateDate=" + LastUpdateDate +
                ", DelMark=" + DelMark +
                '}';
    }
}
