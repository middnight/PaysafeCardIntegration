package com.scaler.assignment.roiimpoc;

import com.paysafe.PaysafeApiClient;
import com.paysafe.cardpayments.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RoiimPocApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(RoiimPocApplication.class, args);



	}

}
