package com.wxj.springboot.mybatis.domain.dto;

import com.wxj.springboot.mybatis.domain.entity.Address;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName CompanyDTO.java
 * @Description TODO
 * @createTime 2022年07月02日 13:46:00
 */
@Data
public class CompanyDTO {
    private Long id;
    private String name;
    private Integer personNum;
    private LocalDateTime createTime;
    private String creator;
    private List<Address> adds;
}