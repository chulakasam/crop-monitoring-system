package com.example.Proposed.Crop.monitoring.system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProposedCropMonitoringSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProposedCropMonitoringSystemApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}



}
