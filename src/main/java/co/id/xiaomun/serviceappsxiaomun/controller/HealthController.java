package co.id.xiaomun.serviceappsxiaomun.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class HealthController {

    @GetMapping("/")
    public String index() {
        return "XIOMUN Backend is running!";
    }

    @GetMapping("/health")
    public String health() {
        return "healthy";
    }

}
