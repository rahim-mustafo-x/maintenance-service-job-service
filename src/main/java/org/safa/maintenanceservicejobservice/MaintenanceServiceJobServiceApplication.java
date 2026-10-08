package org.safa.maintenanceservicejobservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MaintenanceServiceJobServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MaintenanceServiceJobServiceApplication.class, args);
    }
}
