package com.wxj.springboot.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName User5VO.java
 * @Description TODO
 * @createTime 2022年07月02日 23:14:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User5VO {

    private Integer id;
    private String name;
    private String type;
}
