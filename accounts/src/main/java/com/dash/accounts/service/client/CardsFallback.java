package com.dash.accounts.service.client;

import com.dash.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        //For now this is returning null, so that if one dependent class is down, for example cards here
        // the other dependent loans will still be able to send the response.
        // In actual business logic there will be some use case to return the response.
        return null;
    }

}

