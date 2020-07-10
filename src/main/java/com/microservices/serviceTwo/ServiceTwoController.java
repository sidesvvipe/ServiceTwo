package com.microservices.serviceTwo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.awt.*;
import java.net.URI;

@RestController
public class ServiceTwoController {

    private final RestTemplate restTemplate;

    public ServiceTwoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "check-input",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public String checkInput(@RequestBody Object objToCheck) {
        ResponseEntity<Response> responseEntity = this.restTemplate.exchange(
                RequestEntity.post(URI.create("http://localhost/is-number")).contentType(MediaType.APPLICATION_JSON).body(objToCheck),
                Response.class);
        if (responseEntity.getStatusCode() == HttpStatus.ACCEPTED) {
            return "YESSSSS, Its a num";
        }
        return  "obj not num";
    }
}
