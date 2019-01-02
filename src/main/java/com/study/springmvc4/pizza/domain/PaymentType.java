package com.study.springmvc4.pizza.domain;

import java.util.Arrays;
import java.util.List;

public enum PaymentType {
    CASH,CHECK,CREDIT_CARD,ALIPAY,WECHAT_PAY;
    public static List<PaymentType> asList(){
        PaymentType[] paymentTypes = PaymentType.values();
        return Arrays.asList(paymentTypes);
    }
    
}
