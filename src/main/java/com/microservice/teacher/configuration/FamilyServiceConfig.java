package com.microservice.teacher.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FamilyServiceConfig {

  @Value("${config.base.family.uri}")
  private String pathFamily;

  @Bean
  public WebClient registrarWebClient() {
    return WebClient.create(pathFamily);
  }

}
