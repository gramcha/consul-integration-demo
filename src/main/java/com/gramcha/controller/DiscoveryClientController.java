/**
 * @author gramcha
 * 19-Jun-2018 1:21:59 PM
 * 
 */
package com.gramcha.controller;

import java.net.URI;

import java.util.Optional;

import javax.naming.ServiceUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@RestController
@RefreshScope
public class DiscoveryClientController {
	@Autowired
    private DiscoveryClient discoveryClient;
 
//	@Value("${env.welcomeMessage}")
    String welcomeMessage;
	
    public Optional<URI> serviceUrl(String appInstanceId) {
        return discoveryClient.getInstances(appInstanceId)
          .stream()
          .map(si -> si.getUri())
          .findFirst();
    }
    @RequestMapping("/discoveryClient")
    public String discoveryPing() throws RestClientException, 
      ServiceUnavailableException {
    		RestTemplate restTemplate = new RestTemplate();
    		System.out.println(serviceUrl("consul-integration-demo"));
        URI service = serviceUrl("consul-integration-demo")
          .map(s -> s.resolve("/ping"))
          .orElseThrow(ServiceUnavailableException::new);
        return restTemplate.getForEntity(service, String.class)
          .getBody();
    }
     
    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }
    
    @GetMapping("/app-health-check")
    public String myCustomCheck() {
        return "I am okay.";
    }
    
    @RequestMapping("/welcomeMessage")
    public String welcomeMessage() {
        return welcomeMessage;
    }
}
