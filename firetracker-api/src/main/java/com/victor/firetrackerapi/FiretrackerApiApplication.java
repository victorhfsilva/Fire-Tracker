package com.victor.firetrackerapi;

import com.victor.firetrackerapi.model.Data;
import com.victor.firetrackerapi.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class FiretrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiretrackerApiApplication.class, args);
	}


/*	//Only for Test: Populate the database with dummy data
	@Autowired
	private DataRepository dataRepository;
	@Bean
	public void populateDatabase() {
		for (int i = 0; i < 1; i++) {
			Data data = new Data();
			data.setInsertionDateTime(LocalDateTime.now().minusHours(24).plusMinutes(i * 15));
			//data.setOnFire(Math.random() < 0.5);
			data.setOnFire(false);
			data.setIrLevel(Math.random() * 100);
			//data.setTemperature(Math.random() * 50);
			data.setTemperature(27.2);
			dataRepository.save(data);
		}
	}*/
}



