package com.ght.graduationsys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

@Data
@ExcelTarget("task")
public class Task implements Serializable {

    private int id;


    @Excel(name = "课题方向", width = 30)
    private String name;


    private String major;//该课题所属专业

    private int majorid;
}
