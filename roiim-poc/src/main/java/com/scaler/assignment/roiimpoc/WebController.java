package com.scaler.assignment.roiimpoc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paysafe.cardpayments.Authorization;
import com.paysafe.cardpayments.Status;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Optional;

// web controller for the application
@Controller
public class WebController {
    @Autowired
    private PaymentController paymentController;
    @Autowired
    private PaysafeConfigProvider paysafeConfigProvider;

    private final String HOME="home.html";
    private final String ERROR="error.html";
    private final String SUCCESS="success.html";



    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return HOME;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String doPayment(@ModelAttribute("orderDto")PaymentOrderDto orderDto, Model model){

        Authorization res=null;
        try{
            //creating the auth object instance from the received dto
            Authorization auth= Authorization.builder().
                    merchantRefNum(orderDto.getMerchantRefNumber()).
                    amount(orderDto.getAmount()).
                    card().
                    cardNum(orderDto.getCardNumber()).
                    cvv(orderDto.getCvvNumber()).
                    cardExpiry().
                    month(orderDto.getCardExpiryMonth()).
                    year(orderDto.getCardExpiryYear()).
                    done().
                    done().
                    billingDetails().
                    // TODO : was giving invalid param error, need to check
                    //street(orderDto.getStreet()).
                    //city(orderDto.getCity()).
                    //state(orderDto.getState()).
                    //country(orderDto.getCountry()).
                    zip(orderDto.getZip()).
                    done().
                    build();

            // calling the Payment controller to do the payment
            Optional<Authorization> response=paymentController.initiatePayment(auth);

            if(response.isPresent()){
                res=response.get();
            }

            //logging for debugging purposes using jackson, can refer paysafe docs
            if(res!=null) {
                ObjectMapper obj=new ObjectMapper();
                System.out.println(obj.writeValueAsString(res));
            }


        }catch (Exception ex){
            //TODO : implement better exception handling for Payment Related errors
            model.addAttribute("errorMessage",ex.getMessage());
            model.addAttribute("stackTrace", ExceptionUtils.getStackTrace(ex));
            ex.printStackTrace();
        }

        return res!=null && res.getStatus().equals(Status.COMPLETED) ? SUCCESS : ERROR;
    }

}
