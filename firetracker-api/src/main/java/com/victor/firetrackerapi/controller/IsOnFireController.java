package com.victor.firetrackerapi.controller;

import com.victor.firetrackerapi.model.Data;
import com.victor.firetrackerapi.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IsOnFireController {

    @Autowired
    private DataRepository repository;

    @GetMapping("/firetracker/isOnFire")
    public boolean getOnFire(){
        Data data = repository.getLastInsertedData();
        return data.getOnFire();
    }

}
