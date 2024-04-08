package com.rharhuky.notificationapp.config.amazon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnsConfiguration {

    @Value(value = "${aws.accessKey}")
    private String accessKey;

    @Value(value = "${aws.accessKey}")
    private String secretKey;

}
