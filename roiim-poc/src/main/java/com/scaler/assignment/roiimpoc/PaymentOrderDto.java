package com.scaler.assignment.roiimpoc;

//Dto for Payment orders


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentOrderDto {

    private int amount;
    private String merchantRefNumber;
    private boolean settleWithAuth=true;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String cardNumber;
    private String cvvNumber;
    private int cardExpiryMonth;
    private int cardExpiryYear;



}
