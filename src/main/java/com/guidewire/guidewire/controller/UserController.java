package com.guidewire.guidewire.controller;

import com.guidewire.guidewire.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @PostMapping("/save-profile")
    public Map<String, String> saveProfile(@RequestBody User userData) {

        System.out.println("User saved:");
        System.out.println("Phone: " + userData.getPhone());
        System.out.println("Name: " + userData.getName());
        System.out.println("City: " + userData.getCity());
        System.out.println("Vehicle: " + userData.getVehicle());
        System.out.println("Platform: " + userData.getPlatform());
        System.out.println("Experience: " + userData.getExperience());
        System.out.println("Bank: " + userData.getBankName());

        return Map.of("status", "success");
    }
}