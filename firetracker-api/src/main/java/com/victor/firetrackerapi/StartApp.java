package com.victor.firetrackerapi;

import com.victor.firetrackerapi.model.Data;
import com.victor.firetrackerapi.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class StartApp implements CommandLineRunner {

    @Autowired
    private DataRepository repository;


    @Override
    public void run(String... args) throws Exception {
        Data data1 = new Data();
        data1.setOnFire(false);
        data1.setIrLevel(1.0);
        data1.setTemperature(25.0);
        data1.setActive(true);
        data1.setInsertionDateTime(LocalDateTime.now());
        repository.save(data1);

        Data data2 = new Data();
        data2.setOnFire(true);
        data2.setIrLevel(2.5);
        data2.setTemperature(30.0);
        data2.setActive(false);
        data2.setInsertionDateTime(LocalDateTime.of(2023, 4, 9, 15, 30));
        repository.save(data2);

        Data data3 = new Data();
        data3.setOnFire(false);
        data3.setIrLevel(3.0);
        data3.setTemperature(20.0);
        data3.setActive(true);
        data3.setInsertionDateTime(LocalDateTime.of(2023, 4, 8, 10, 15));
        repository.save(data3);


        List<Data> data = repository.findAll();
        for (Data d : data){
            System.out.println(d);
        }
    }
}
