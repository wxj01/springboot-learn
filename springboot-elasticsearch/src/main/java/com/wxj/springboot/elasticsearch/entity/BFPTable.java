package com.wxj.springboot.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *  商务、应付、资金 中间关系表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BFPTable {

    private Long docId;

    private String supplierId;  // 供应商id
    private String contractId; // 合同主键
    private String orderId; // 订单id

    private String InvoiceListId;  // 发票清单id
    private String invoiceId; // 发票id
    private String paymentProposalsId; // 付款建议id

    private String paymentRequestFormId; //付款申请单id
    private String billId; // 票据id
    private String waterId;  // 流水id

}
