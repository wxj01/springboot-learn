package com.wxj.springboot.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 商务领域宽表   一个供应商 -> 多个合同 -> 多个订单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BusinessWideTable {

    private Long docId;
    /**
     * 供应商 -> 多个合同 
     */
    private String supplierId;  // 供应商id
    private String companyName; // 供应名称
//    private String companyCode; //供应商编码
    private String setTime; // 供应商成立时间

    /**
     * 合同 -> 多个订单
     */
    private String contractId; // 合同主键
//    private String ContractNo; //归档合同号
    private String contractName; // 合同名称
    private String contractTime; // 合同发布时间
    private String contractCompanyId; // 供应商id
//    private String contractCompanyCode; // 供应商编码
//    private String contractCompanyName; // 供应商名称

    /**
     * 订单
     */
    private String orderId; // 订单id
    private String orderName; //订单名称
    private String orderTime; //订单创建时间
    private String orderContractId;//合同Id
//    private String orderContractNo;//合同编号
//    private String orderContractName;// 合同名称
//    private String orderCompanyCode; // 供应商编码
//    private String orderCompanyName;//供应商名称


}
