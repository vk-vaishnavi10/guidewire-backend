package com.guidewire.guidewire.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {

    // Temporary in-memory store (instead of DB)
    private Map<String, String> otpStore = new HashMap<>();

    // 🔹 Generate OTP
    public String sendOtp(String phone) {

        String otp = String.valueOf((int)(Math.random() * 900000) + 100000); // fixed OTP for demo

        otpStore.put(phone, otp);
        System.out.println("OTP for " + phone + " is: " + otp);

        return otp;
    }

    // 🔹 Verify OTP
    public boolean verifyOtp(String phone, String otp) {

        String storedOtp = otpStore.get(phone);

        if (storedOtp == null) return false;

        return storedOtp.equals(otp);
    }
}