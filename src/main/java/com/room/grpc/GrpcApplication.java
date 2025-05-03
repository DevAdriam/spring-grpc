package com.room.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GrpcApplication.class);
        app.run(args);
	}

}
