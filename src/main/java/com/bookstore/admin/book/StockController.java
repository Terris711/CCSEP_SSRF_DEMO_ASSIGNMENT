package com.bookstore.admin.book;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StockController {
    @PostMapping("/endpoint")
    public String processEndpointUrl(@RequestBody Map<String, String> payload) {
        String url = payload.get("url");

        // Use the provided URL to fetch stock details from another service
        String stock = fetchStockFromExternalService(url);


        return stock;
    }

    private String fetchStockFromExternalService(String url) {
        // Use any HTTP client like RestTemplate or WebClient to fetch data from the provided URL
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
    @GetMapping("/stock")
    private String getStock() {
        return "10";
    }

}
