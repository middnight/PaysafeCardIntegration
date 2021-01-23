package com.scaler.assignment.roiimpoc;

import com.paysafe.Environment;
import com.paysafe.PaysafeApiClient;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PaysafeApiClientProvider {

    private PaysafeApiClient paysafeApiClient=null;
    private boolean isInstanceAvailable=false;

    @Autowired
    PaysafeConfigProvider paysafeConfigProvider;

    // singleton pattern diluted implementation
    // i don't see any reason to initialize new PaySafeAPiClient for each request
    public  PaysafeApiClient getApiClient() throws Exception{
        if(!isInstanceAvailable && paysafeApiClient==null){
            switch ( paysafeConfigProvider.getPaysafeEnvironment()){
                case "TEST":
                    paysafeApiClient= new PaysafeApiClient(paysafeConfigProvider.getPaysafeApiKey(), paysafeConfigProvider.getPaysafeApiPassword(), Environment.TEST, paysafeConfigProvider.getPaysafeAccountNumber());
                    break;

                case "LIVE":
                    paysafeApiClient= new PaysafeApiClient(paysafeConfigProvider.getPaysafeApiKey(), paysafeConfigProvider.getPaysafeApiPassword(), Environment.LIVE);
                    break;
                default:
                    throw new IllegalArgumentException("Environment value can only be TEST/LIVE ");

            }
            isInstanceAvailable=true;
        }
        return paysafeApiClient;
    }


}
