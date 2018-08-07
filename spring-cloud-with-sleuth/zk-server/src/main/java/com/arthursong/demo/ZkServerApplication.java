package com.arthursong.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class ZkServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZkServerApplication.class, args);
	}
}
