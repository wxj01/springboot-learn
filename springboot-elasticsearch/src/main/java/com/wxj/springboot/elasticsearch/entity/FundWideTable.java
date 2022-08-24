package com.wxj.springboot.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 资金宽表  一个付款申请单-> 多个票据， 一个票据-> 多个流水
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FundWideTable {

    private Long docId;

    /**
     *  应付 关联 资金
     */
    private String paymentProposalsId; // 付款建议id
    private String paymentProposalsName;// 建议内容

    /**
     * 付款申请单
     */
//    付款申请单号（ZDJBH）
//    业务单号（ZYWDH）
//    公司（BUKRS）
//    银行名称（ZBANKA_SK）、对共对私（ZDGDS，10-对公/11-对私）
//    付款发送时间（ZFKFSSJ）
    private String paymentRequestFormId; //付款申请单id
    private String paymentRequestFormName; //付款申请单名称
    private String paymentRequestFormTime; // 付款申请单时间
    /**
     * 票据、金票信息
     */
    private String billId; // 票据id
    private String billName; // 票据名称
    private String billTime; // 票据时间
    private String billPaymentRequestFormId; // 付款申请单id
    /**
     * 流水、回单
     */

    private String waterId;  // 流水id
    private String waterInfo; // 流水详情
    private String waterTime; // 流水时间
    private String waterBillId; // 票据id
}
