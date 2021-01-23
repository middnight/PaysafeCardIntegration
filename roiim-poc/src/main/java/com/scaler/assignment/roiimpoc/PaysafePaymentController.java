package com.scaler.assignment.roiimpoc;

import com.paysafe.PaysafeApiClient;
import com.paysafe.cardpayments.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Optional;

//Controller to handle all paysafe operations
@Controller
public class PaysafePaymentController implements  PaymentController{

    @Autowired
    PaysafeConfigProvider paysafeConfigProvider;

    @Autowired
    PaysafeApiClientProvider paysafeApiClientProvider;


    @Override
    public Optional<Authorization> initiatePayment(Authorization authorization) throws Exception{
        PaysafeApiClient paysafeApiClient=paysafeApiClientProvider.getApiClient();
        Authorization auth=null;
        if(isAvailable()){
             auth=paysafeApiClient.cardPaymentService().authorize(authorization);
        }
        else {
            throw new UnsupportedOperationException("Payment gateway is not available at the moment, Please try after some time");
        }
        Optional<Authorization> authResponce=Optional.ofNullable(auth);
        return  authResponce;
    }

    @Override
    public Optional<Authorization> initiateRefund() throws Exception{
        throw  new NotImplementedException();
    }

    @Override
    public boolean isAvailable() throws Exception {
        boolean isAvailable = paysafeApiClientProvider.getApiClient().cardPaymentService().monitor();
        return isAvailable;
    }
}
