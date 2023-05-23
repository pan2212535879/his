package com.abc.His02.entity.OutpatientFinancial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptWorkload {
    private Integer ID;// ID 自增长类型
    private String DeptCode;// 科室编码 如：SJNK
    private String DeptName;// 科室名称 如：神经内科
    private Integer DeptCategoryID;// 科室分类 指向ConstantItem(ID)
    private Integer DeptType;// 科室类型 1-临床 2-医技 3-财务 4-行政 5-其它
    private Integer DelMark;// 删除标记 1-正常 0-已删除
    private float ghf = 0;
    private float zlf = 0;
    private float jyf = 0;
    private float jyclf = 0;
    private float csjcf = 0;
    private float csclf = 0;
    private float fsjcf = 0;
    private float fsclf = 0;
    private float MRIjcf = 0;
    private float MRIclf = 0;
    private float CTjcf = 0;
    private float CTclf = 0;
    private float xyf = 0;
    private float zcyf = 0;
    private float cyf = 0;
    private float czf = 0;
    private float czclf = 0;
    private float mzf = 0;
    private float mzyf = 0;
    private float mzssf = 0;
    private float qt = 0;

    @Override
    public String toString() {
        return "DeptWorkload{" +
                "ID=" + ID +
                ", DeptCode='" + DeptCode + '\'' +
                ", DeptName='" + DeptName + '\'' +
                ", DeptCategoryID=" + DeptCategoryID +
                ", DeptType=" + DeptType +
                ", DelMark=" + DelMark +
                ", ghf=" + ghf +
                ", zlf=" + zlf +
                ", jyf=" + jyf +
                ", jyclf=" + jyclf +
                ", csjcf=" + csjcf +
                ", csclf=" + csclf +
                ", fsjcf=" + fsjcf +
                ", fsclf=" + fsclf +
                ", MRIjcf=" + MRIjcf +
                ", MRIclf=" + MRIclf +
                ", CTjcf=" + CTjcf +
                ", CTclf=" + CTclf +
                ", xyf=" + xyf +
                ", zcyf=" + zcyf +
                ", cyf=" + cyf +
                ", czf=" + czf +
                ", czclf=" + czclf +
                ", mzf=" + mzf +
                ", mzyf=" + mzyf +
                ", mzssf=" + mzssf +
                ", qt=" + qt +
                '}';
    }
}
