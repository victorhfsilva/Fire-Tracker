package com.victor.firetrackerapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.victor.firetrackerapi.model.Data;
import com.victor.firetrackerapi.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    private DataRepository repository;

    @GetMapping("/firetracker/data")
    public List<Data> getData(){
        return repository.findAll();
    }

    /* Request Format:
    http://localhost:8080/data/get?startDateTime=2023-04-09T10:00:00&endDateTime=2023-04-09T14:00:00
     */
    @GetMapping("/firetracker/data/get")
    public List<Data> getDataInBetween(
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {

        return repository.findDataBetween(startDateTime, endDateTime);
    }

    @GetMapping("/firetracker/data/getLast")
    public Data getLastInsertedData(){
        return repository.getLastInsertedData();
    }

    @DeleteMapping("/firetracker/data/clean")
    public void clean(){
        repository.deleteAll();
    }

    @DeleteMapping("/firetracker/data/del")
    public void delDataInBetween(
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {

        repository.deleteDataBetween(startDateTime, endDateTime);
    }

    @PostMapping("firetracker/data/new")
    public void saveData(
            @RequestParam("isOnFire") Boolean isOnFire,
            @RequestParam("irLevel") Double irLevel,
            @RequestParam("temperature") Double temperature
    ){
        Data data = new Data();
        data.setInsertionDateTime(LocalDateTime.now());
        data.setOnFire(isOnFire);
        data.setIrLevel(irLevel);
        data.setTemperature(temperature);
        repository.save(data);
    }

}
