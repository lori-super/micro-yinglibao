package com.micro.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "p2p.alisms")
@Data
public class AliSmsProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
}
