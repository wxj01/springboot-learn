package com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/22 0022 20:45
 */
@Entity(name = "Student")
@Data
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sNo;
    private String sName;
    private String sSex;
    private Integer sAge;
    private String sDept;
}