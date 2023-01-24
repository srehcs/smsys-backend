package com.example.smsys.service.implementation;

import com.example.smsys.enumeration.Status;
import com.example.smsys.model.Smsys;
import com.example.smsys.repo.SmsysRepo;
import com.example.smsys.service.SmsysService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.TRUE;

/**
 * @author srehcs
 */

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class SmsysServiceImpl implements SmsysService {
    private final SmsysRepo smsysRepo;

    @Override
    public Smsys create(Smsys student) {
        log.info("Saving new student: {}", student.getName());
        student.setImageUrl(setSmsysImageUrl()); // Scroll to bottom
        return smsysRepo.save(student); // using saveJPA ext. --> previously null
    }

    @Override
    public Smsys ping(String ipAddress) throws IOException {
        log.info("Pinging student IP: {}", ipAddress);
        Smsys smsys = smsysRepo.findByIpAddress(ipAddress); // here lowercase is eq. to an arbitrary student
        InetAddress address = InetAddress.getByName(ipAddress);
        smsys.setStatus(address.isReachable(10000) ? Status.STUDENT_UP : Status.STUDENT_DOWN);
        smsysRepo.save(smsys);
        return smsys;
        // wip 5-5
    }

    @Override
    public Collection<Smsys> list(int limit) {
        log.info("Fetching all students");
        return smsysRepo.findAll(PageRequest.of(0, limit)).toList();

    }

    @Override
    public Smsys get(Long id) {
        log.info("Fetching student by id: {}", id);
        return smsysRepo.findById(id).orElse(null); // Wanted to use ".get();" but errorWarning
    }

    @Override
    public Smsys update(Smsys smsys) {
        log.info("Updating student: {}", smsys.getName());
        return smsysRepo.save(smsys); // using saveJPA ext. --> previously null
    }


    @Override
    public Boolean delete(Long id) {
        log.info("Deleting student by ID: {}", id);
        smsysRepo.deleteById(id);
        return TRUE;

    }


    private String setSmsysImageUrl() { // Note: custom method, come back to this later
        String[] imageNames = {"stu1.png", "stu2.png", "stu3.png", "stu4.png", "stu5.png", "stu6.png", "stu7.png", "stu8.png", "stu9.png", "stu10.png",};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/smsys/img-stu/" + imageNames[new Random().nextInt(9)]).toUriString(); // 7-2
    }
}
