package com.guidewire.guidewire.repository;

import com.guidewire.guidewire.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpVerification, Long> {
    Optional<OtpVerification> findTopByPhoneOrderByExpiryTimeDesc(String phone);
}