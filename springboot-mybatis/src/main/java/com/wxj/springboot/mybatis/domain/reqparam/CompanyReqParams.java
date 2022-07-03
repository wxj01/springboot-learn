package com.wxj.springboot.mybatis.domain.reqparam;

import lombok.Data;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName CompanyReqParams.java
 * @Description 公司类的请求参数
 * @createTime 2022年07月03日 10:09:00
 */
@Data
public class CompanyReqParams {

    private Long id;
    private String name;
    private Integer personNum;
    private String creator;
}
