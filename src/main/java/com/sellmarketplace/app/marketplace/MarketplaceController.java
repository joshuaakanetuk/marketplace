package com.sellmarketplace.app.marketplace;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketplaceController {

    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean(String.format("Hello World! %s", System.currentTimeMillis()));
    }
}
