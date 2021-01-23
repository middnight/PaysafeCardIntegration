package com.scaler.assignment.roiimpoc;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


// paysafe related config data provider
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
//@Setter
@Configuration
@PropertySource("classpath:paysafe.properties")
public class PaysafeConfigProvider {

    @Value("${paysafeApiKey}")
    private String paysafeApiKey;

    @Value("${paysafeApiPassword}")
    private String paysafeApiPassword;

    @Value("${paysafeAccountNumber}")
    private String paysafeAccountNumber;

    @Value("${paysafeCurrencyCode}")
    private String paysafeCurrencyCode;

    @Value("${paysafeCurrencyBaseUnitsMultiplier}")
    private int paysafeCurrencyBaseUnitsMultiplier;

    @Value("${paysafeEnvironment}")
    private String paysafeEnvironment;


}
