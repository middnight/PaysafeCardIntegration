package com.scaler.assignment.roiimpoc;

import com.paysafe.cardpayments.Authorization;
import com.paysafe.cardpayments.Status;
import com.paysafe.common.InvalidRequestException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PaySafePaymentControllerTests {

    @Autowired
    private PaysafePaymentController paysafePaymentController;

    @Test
    public void checkInitiatePaymentSuccess() {
        Authorization res = null;
        Authorization auth =
                Authorization.builder()
                        .merchantRefNum(RandomStringUtils.random(15, true, true))
                        .amount(1)
                        .card()
                        .cardNum("5100400000000000")
                        .cvv("111") // check https://developer.paysafe.com/en/payments/api/#/introduction/testing-instructions/test-cards
                        .cardExpiry()
                        .month(2)
                        .year(2022)
                        .done()
                        .done()
                        .billingDetails()
                        .zip("M5H 2N2")
                        .done()
                        .build();

        try {
            Optional<Authorization> optionalAuthorization = paysafePaymentController.initiatePayment(auth);
            res = optionalAuthorization.get();

        } catch (Exception ex) {

        }

        assertThat(res.getStatus().equals(Status.COMPLETED));

    }

    @Test
    public void checkInitiatePaymentFailure() {
        Authorization res = null;
        Authorization auth =
                Authorization.builder()
                        .merchantRefNum(RandomStringUtils.random(15, true, true))
                        .amount(1)
                        .card()
                        .cardNum("5100400000000000")
                        .cvv("111") // check https://developer.paysafe.com/en/payments/api/#/introduction/testing-instructions/test-cards
                        .cardExpiry()
                        .month(2)
                        .year(2020)
                        .done()
                        .done()
                        .billingDetails()
                        .zip("M5H 2N2")
                        .done()
                        .build();

        try {
            Optional<Authorization> optionalAuthorization = paysafePaymentController.initiatePayment(auth);
            res = optionalAuthorization.get();

        } catch (InvalidRequestException ex) {
            assertThat(ex.getCode().equals("3006"));
        } catch (Exception exception) {
            assertThat(exception.getMessage().equalsIgnoreCase("You submitted an expired credit card number with your request."));
        }

    }

    @Test
    public void isControllerWorking() {
        boolean isAvailable = false;
        try {
            isAvailable = paysafePaymentController.isAvailable();
        } catch (Exception ex) {
        }
        assertEquals(isAvailable, true);

    }

}