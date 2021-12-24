package com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @Data   ：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
 * @date 2021/12/22 0022 20:45
 */
@Entity(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;

    public Customer(String email, String name) {
        this.email = email;
        this.name = name;
    }
}