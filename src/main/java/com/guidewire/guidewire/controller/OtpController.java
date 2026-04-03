package com.guidewire.guidewire.controller;

import com.guidewire.guidewire.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin // allow frontend (React)
public class OtpController {

    @Autowired
    private OtpService otpService;

    // 🔹 SEND OTP
    @PostMapping("/send-otp")
    public Map<String, Object> sendOtp(@RequestBody Map<String, String> request) {

        String phone = request.get("phone");

        String otp = otpService.sendOtp(phone);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "OTP sent successfully");
        response.put("otp", otp); // ⚠️ for testing only

        return response;
    }

    // 🔹 VERIFY OTP
    @PostMapping("/verify-otp")
    public Map<String, Object> verifyOtp(@RequestBody Map<String, String> request) {

        String phone = request.get("phone");
        String otp = request.get("otp");

        boolean isValid = otpService.verifyOtp(phone, otp);

        Map<String, Object> response = new HashMap<>();

        if (isValid) {
            response.put("message", "OTP verified");
            response.put("status", "success");
        } else {
            response.put("message", "Invalid OTP");
            response.put("status", "fail");
        }

        return response;
    }
}