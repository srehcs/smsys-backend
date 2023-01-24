package com.example.smsys.resource;

import com.example.smsys.model.Response;
import com.example.smsys.model.Smsys;
import com.example.smsys.service.implementation.SmsysServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import static java.util.Map.of;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.example.smsys.enumeration.Status.STUDENT_UP;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

/**
 * @author srehcs
 */

// This is the same thing as a traditional controller

@RestController
@RequestMapping("/smsys")
@RequiredArgsConstructor // dependency injection for 18
public class SmsysResource {
    private final SmsysServiceImpl smsysService;
    @GetMapping("/list")
    public ResponseEntity<Response> getSmsyss() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("students", smsysService.list(30)))
                        .message("Students retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

   @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingSmsys(@PathVariable("ipAddress") String ipAddress) throws IOException { // see impl for reasoning
        Smsys smsys = smsysService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("student", smsysService.list(30)))
                        .message(smsys.getStatus() == STUDENT_UP ? "Students pinged successfully" : "Students pinged failure")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveSmsys(@RequestBody @Valid Smsys smsys){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("student", smsysService.create(smsys)))
                        .message("student created")
                        .status(CREATED) // 201 status
                        .statusCode(CREATED.value()) // ^^ see HTTP
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getSmsys(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("student", smsysService.get(id)))
                        .message("student retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteSmsys(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted", smsysService.delete(id)))
                        .message("student deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping(path = "/img-stu/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getSmsysImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/img-stu/" + fileName));
    }

}
