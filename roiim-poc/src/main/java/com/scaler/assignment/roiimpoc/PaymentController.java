package com.scaler.assignment.roiimpoc;

import com.paysafe.cardpayments.Authorization;

import java.util.Optional;

public interface PaymentController {

    Optional<Authorization> initiatePayment(Authorization authorization) throws Exception;

    Optional<Authorization> initiateRefund() throws Exception;

    boolean isAvailable () throws Exception ;




}
