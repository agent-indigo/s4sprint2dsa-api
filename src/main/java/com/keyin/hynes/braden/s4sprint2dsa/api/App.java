package com.keyin.hynes.braden.s4sprint2dsa.api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {
    "com.keyin.hynes.braden.s4sprint2dsa.api.controllers",
    "com.keyin.hynes.braden.s4sprint2dsa.api.entities",
    "com.keyin.hynes.braden.s4sprint2dsa.api.services",
    "com.keyin.hynes.braden.s4sprint2dsa.api.interfaces.repositories"
})
public class App {
    public App() {}
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}