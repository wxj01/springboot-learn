package com.wxj.springboot.mybatis.domain.vo;

import com.wxj.springboot.mybatis.domain.entity.Address;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName CompanyVO.java
 * @Description TODO
 * @createTime 2022年07月02日 13:08:00
 */
@Data
public class CompanyVO {
    private Long id;
    private String name;
    private Integer personNum;
    private LocalDateTime createTime;
    private String creator;
    private List<Address> adds;
}
