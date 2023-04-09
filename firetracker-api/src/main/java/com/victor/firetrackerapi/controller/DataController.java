package com.victor.firetrackerapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.victor.firetrackerapi.model.Data;
import com.victor.firetrackerapi.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
