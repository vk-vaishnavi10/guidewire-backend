package com.guidewire.guidewire.controller;

import com.guidewire.guidewire.entity.User;
import com.guidewire.guidewire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save-profile")
    public User saveProfile(@RequestBody User userData) {

        Optional<User> userOptional = userRepository.findByPhone(userData.getPhone());

        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = new User();
            user.setPhone(userData.getPhone());
            user.setVerified(true); // OTP already verified
        }

        // 🔹 PROFILE DATA
        user.setName(userData.getName());
        user.setCity(userData.getCity());
        user.setVehicle(userData.getVehicle());

        if (userData.getPlatform() != null) {
            user.setPlatform(userData.getPlatform());
        }

        user.setExperience(userData.getExperience());

        // 🔥 BANK DETAILS (THIS WAS MISSING)
        user.setBankHolder(userData.getBankHolder());
        user.setAccountNumber(userData.getAccountNumber());
        user.setIfsc(userData.getIfsc());
        user.setBankName(userData.getBankName());

        return userRepository.save(user);
    }
}