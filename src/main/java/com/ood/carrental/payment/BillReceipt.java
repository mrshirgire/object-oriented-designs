package com.ood.carrental.payment;

import com.ood.carrental.enums.PaymentStatus;
import lombok.Data;

@Data
public class BillReceipt {

    private String billNumber;
    private String billDate;
    private String billType;
    private String billAmount;
    private PaymentStatus paymentStatus;

}
