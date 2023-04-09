package com.victor.firetrackerapi.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.stereotype.Service;

@Service
public class IpAddressService {

    public String getLocalIPAddress() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
}