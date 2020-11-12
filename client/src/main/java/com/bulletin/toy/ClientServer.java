package com.bulletin.toy;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@EnableFeignClients
@SpringBootApplication
public class ClientServer {

    public static void main(String[] args) {
        SpringApplication.run(ClientServer.class,args);
    }

}
