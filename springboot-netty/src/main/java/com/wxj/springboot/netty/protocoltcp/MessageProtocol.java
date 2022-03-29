package com.wxj.springboot.netty.protocoltcp;

import lombok.Data;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName MessageProtocol.java
 * @Description TODO
 * @createTime 2022年03月29日 22:03:00
 */
@Data
public class MessageProtocol {

    private int len;
    private byte[] content;


}
