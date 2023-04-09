package com.victor.firetrackerapi.controller;

import com.victor.firetrackerapi.service.IpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    IpAddressService ipAddressService;

    @GetMapping("/firetracker")
    public String welcomeToFireTracker() {
        return "Welcome to Fire Tracker. We are here to make your life safer.";
    }

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("/")
    public String welcomeToMyAddress() {
        String ipAddress = ipAddressService.getLocalIPAddress();
        return "You successfully connected to " + ipAddress + ":" + serverPort+".";
    }


}
