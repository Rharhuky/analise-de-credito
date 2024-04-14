package com.rharhuky.notificationapp.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SnsService {

    private final AmazonSNS amazonSNS;

    public void notificar(String message, String telefone){
        PublishRequest publishRequest = new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(telefone);
        amazonSNS.publish(publishRequest);
    }
}
