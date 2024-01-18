package com.wxj.springboot.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 应付宽表  发票清单 -> 多个发票 ，发票 -> 多个建议
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PayableWideTable {

    private Long docId;

    /**
     * 用于商务和应付的关联
      */
    private String orderId; // 订单id
    private String orderName; //订单名称
    /**
     * 发票清单 Invoice list
     */
    private String InvoiceListId;  // 发票清单id
    private String InvoiceListName; //发票清单名称
    private String InvoiceListTime; //发票清单时间
    /**
     * 发票信息
     */
//    发票号码（invoice_no）
//    发票代码（invoice_code）
//    发票日期（invoice_date）
//    购买方名称（buyer_name）
//    销售方名称（saler_name）
//    金额（invoice_amount）

    private String invoiceId; // 发票id
    private String invoiceNo; // 发票号
    private String invoiceCode; // 发票编码
    private String invoiceDate; // 发票日期
    private String  invoiceInvoiceListId; // 发票清单id

    /**
     * 付款建议
     */
    private String paymentProposalsId; // 付款建议id
    private String paymentProposalsName;// 建议内容
    private double paymentProposalsMoney; //支付金额
    private String paymentProposalsTime; // 建议时间
    private String paymentProposalsInvoiceId;// 发票id
}
