package com.hashicorp.consul.democonsul;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@RestController
public class DemoConsulApplication {

  @Value("${spring.application.name}")
  private String appName;

	@Autowired
	private DiscoveryClient discoveryClient;

  @RequestMapping("/")
  public String home() {
    return "Hello World";
	}
	
	@RequestMapping("/instances")
	public List<ServiceInstance> instances() {
		return discoveryClient.getInstances(appName);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoConsulApplication.class, args);
	}
}
