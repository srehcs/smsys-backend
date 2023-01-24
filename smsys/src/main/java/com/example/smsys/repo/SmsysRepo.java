package com.example.smsys.repo;

import com.example.smsys.model.Smsys;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author srehcs
 */
public interface SmsysRepo extends JpaRepository<Smsys, Long> {
    Smsys findByIpAddress(String ipAddress);
}
