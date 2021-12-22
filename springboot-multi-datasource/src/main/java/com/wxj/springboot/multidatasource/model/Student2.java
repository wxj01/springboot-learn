package com.wxj.springboot.multidatasource.model;

import lombok.Data;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/22 0022 10:37
 */
@Data
public class Student2 {
//    Sno char(7) PRIMARY KEY,
//    Sname char(10) NOT NULL,
//    Ssex char(2),
//    Sage tinyint,
//    Sdept char(20)
    private String sNo;
    private String sName;
    private String sSex;
    private Integer sAge;
    private String sDept;
}