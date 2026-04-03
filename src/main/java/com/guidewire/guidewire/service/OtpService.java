package com.guidewire.guidewire.service;

import com.guidewire.guidewire.entity.OtpVerification;
import com.guidewire.guidewire.entity.User;
import com.guidewire.guidewire.repository.OtpRepository;
import com.guidewire.guidewire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    // 🔹 Generate OTP
    public String sendOtp(String phone) {

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        OtpVerification otpEntity = new OtpVerification();
        otpEntity.setPhone(phone);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpEntity.setUsed(false);

        otpRepository.save(otpEntity);

        return otp; // later we will send SMS
    }

    // 🔹 Verify OTP
    public boolean verifyOtp(String phone, String otp) {

        Optional<OtpVerification> latestOtp =
                otpRepository.findTopByPhoneOrderByExpiryTimeDesc(phone);

        if (latestOtp.isEmpty()) return false;

        OtpVerification otpData = latestOtp.get();

        if (otpData.isUsed()) return false;

        if (!otpData.getOtp().equals(otp)) return false;

        if (otpData.getExpiryTime().isBefore(LocalDateTime.now())) return false;

        otpData.setUsed(true);
        otpRepository.save(otpData);

        // mark user verified
        Optional<User> userOptional = userRepository.findByPhone(phone);

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = new User();
            user.setPhone(phone);
        }

        user.setVerified(true);
        userRepository.save(user);

        return true;
    }
}